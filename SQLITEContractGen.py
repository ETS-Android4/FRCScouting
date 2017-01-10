#! /usr/bin/python

_description = '''
This script take in a SQL file with INSERTS and CREATES and transforms
	it into a SQLite contract in Java. Meant to be used with a phpmyadmin
	exported sql file. Defaults assume the FRC 836 file structure.
'''

_defaultRun = '''
	python SQLITEContractGen.py
		--packagename=org.frc836.database
		--classname=FRCScoutingContract
		--infile=FRC_Scouting_Server/scouting.sql
		--outfile=src/org/frc836/database/FRCScoutingContract.java
'''
__author__ = "Jonny"
__version__ = "2.0"
__copyright__ = ""

import re
import sys
import argparse
import os
import csv
try:
	import StringIO #for python 2.x
except:
	from io import StringIO #for python 3.x.

# note to self. Look into the 'textwrap' class for functionality

def _autogenScriptInfo_Str():
	_myscriptname = os.path.basename(__file__)
	s = "-----------------------------------------------------\n"
	s += str(_myscriptname) +" "+ str(__version__) +'\n'
	s += '\n'
	s += "This file was autogenerated with run cmd:\n"
	s += "  \""
	for arg in sys.argv:
		s += arg +" "
	s = s[0:-1] # get rid of trailing space
	s += '\"\n'
	s += '\n'
	s += "python version info:\n"
	s += "  "+ str(sys.version) +'\n'
	s += '\n'
	s += "Please take heed of modifying unnecessarily\n"
	s += "-----------------------------------------------------"
	return s
#==========================================================================================

def indent(s_in="", lvl=1):
	ret = ""
	tabstr = ""
	for i in range(0,lvl):
		tabstr += "\t"
	for ln in s_in.split('\n'):
		ret += tabstr + ln + "\n"
	return chopnewline(ret)
def chopnewline(s_in):
	if len(s_in) == 0:
		return s_in
	ret = s_in
	while(ret[-1]=='\n'):
		ret = ret[0:-1]
	return ret
def getInStr( s, openC="\"", closeC="\"" ):
	return s
def toJavaString( s, maxLnLen=40 ):
	retStr = ""
	for ln in s.split('\n'):
		retStr += '\"'
		retStr += ln + "\\n"
		retStr += '\"'
		retStr += " +"
		retStr += '\n'
	return retStr[0:-6] + '\"' # removes the last newline and plus
	
class SqlColumn:
	def __init__(self, columnName=None, columnType=None,
						isPrimary=False, defaultVal=None,
						nullValid=False, autoIncrement=False,
						isUnsigned=False):
		self.name = str(columnName)
		self.type = str(columnType)
		self.unsigned = isUnsigned
		self.primary = isPrimary
		self.default = defaultVal
		self.notNull = not nullValid
		self.autoInc = autoIncrement
		self.data = list()
	def addRow(self, data):
		self.data.append( str(data) )
	def getRow(self, i_row):
		if i_row >= len(self.data): return None
		else: return self.data[i_row]
	def getColumns(self):
		return len(self.data)
	def toSqLiteType(self):
		#if re.search(r'int\(10\)', self.type):
		if self.name == 'id':
			self.type = "integer"
		if re.search('varchar',self.type):
			self.type = "text"
	def toSqLite(self):
		self.toSqLiteType()
		if self.primary:
			self.unsigned = False
			self.notNull = False
	def createStr(self, creates = True ):
		self.toSqLite()
		s = ""
		if creates:
			s += self.name
		else:
			s += self.name
		if self.unsigned:
			s += " unsigned"
		s += " "+ self.type
		if self.primary:
			s += " primary key"
		if self.notNull:
			s += " NOT NULL"
		if self.autoInc:
			s += " autoincrement"
		if self.default:
			s += " DEFAULT "+ self.default
		return s

class SqlTable:
	def __init__(self, tableName=""):
		self.name = tableName
		self.columns = list()
		self.mapping = list()
		self.n_row = 0
	def findColumnName(self, colName):
		for i in range(0, len(self.columns)):
			if colName == self.columns[i].name:
				return i
		return None
	def getColumnMapping_csv(self, csvFormat):
		self.mapping = list()
		for s in csvFormat.split(','):
			s = s.strip('\t `')
			self.mapping.append( self.findColumnName(s) )
		return self.mapping
	def addColumn(self, column):
		self.columns.append(column)
	def addRow(self, data):
		i_col = 0
		for dat in next(csv.reader([data], quotechar="'", skipinitialspace=True)):
			dat = dat.strip()
			self.columns[self.mapping[i_col]].addRow(dat)
			i_col += 1
		self.n_row += 1
	def getRow(self, i_row):
		ret = list()
		for i in range(0,len(self.mapping)):
			ret.append(self.columns[i].getRow(i_row))
		return ret
	def checkNumRows(self):
		for col in self.columns:
			if len(col.data) != self.n_row:
				print( str(self.n_row) +"|"+ str(len(col.data)) )
				print("ERROR::Rows in "+ self.name +"."+ col.name +" do not match")
				for dat in col.data:
					print( str(dat) )
				return False
		return True
	def getPrimaryKey(self):
		idKey = None
		for column in self.columns:
			if column.primary:
				if idKey: print("ERROR::Multiple Primary Keys!!")
				idKey = column.name
		return str(idKey)
	def _createClassStr_var(self,name=None):
		s = "public static final String"
		if name:
			s += " COLUMN_NAME_"+ name.upper()
			s += " = \""+ name +"\""
		else:
			s += " TABLE_NAME = \""+ self.name +"\""
		return s +";"
	def createStr_Class(self, baseClass=None):
		s = "public static abstract class "
		s += (self.name).upper() +"_Entry"
		if baseClass:
			s += " implements "+ baseClass
		s += " {\n"
		s += indent( self._createClassStr_var()) +"\n"
		for column in self.columns:
			s += indent( self._createClassStr_var(column.name)) +"\n"
		return s +"}"
	def createStr_DropStr(self):
		return "DROP TABLE IF EXISTS "+ self.name +";"
	def createStr_CreateStr(self):
		s = "CREATE TABLE IF NOT EXISTS "+ self.name +" (\n"
		for column in self.columns:
			s += indent(column.createStr(creates=True)) +",\n"
		s = s[0:-2] # gets rid of the final comma
		s += "\n);"
		return s
	def _createStr_InsertStr_row(self, i_row):
		s = ""
		for column in self.columns:
			val = str(column.getRow( i_row ))
			if column.name == self.getPrimaryKey():
				s += "UNION SELECT"
			s += " '"+ val +"',"
		return s[0:-1]
	def _createStr_InsertStr_firstrow(self):
		s = ""
		for column in self.columns:
			val = str(column.getRow(0))
			if column.name == self.getPrimaryKey():
				s += "SELECT"
			s += ' '+ val +" AS "+ column.name
			s += ','
		return s[0:-1]	
	def createStr_InsertStr(self):
		s = ""
		if (self.n_row > 0) and (self.checkNumRows()):
			cnt = 1
			s += "INSERT INTO "+ self.name +'\n'
			s += self._createStr_InsertStr_firstrow() +'\n'
			for row in range(1,self.n_row):
				s += self._createStr_InsertStr_row(row) +'\n'
			return s[0:-1] +';'
		return None
		
class SqlToJava():
	re_GetSqlVar = re.compile(r"[`](?P<var>\w+)[`]")
	re_CreateStatement = re.compile(r'''
			\s* CREATE \s+ TABLE \s+ IF \s+ NOT \s+ EXISTS \s+    # grabs the create statement
			[`] (?P<tablename>\w+) [`]                            # matches the table name
			(?P<body>[^;]+)                                       # matches the body
			[;]
		''',re.VERBOSE)
	re_InsertStatement = re.compile(r'''
			\s* INSERT \s+ INTO \s+       # finds the insert statements
			[`] (?P<tablename>\w+) [`]     # matches the tablename
			\s+ [(] \s*
			(?P<colnames>[^)]+)
			[)] \s* VALUES [^(]*
			(?P<body>[^;]+) [;]
		''',re.VERBOSE)
	re_GetColumn = re.compile(r'''
			(^|\n) \s+
			[`] (?P<name>\w+) [`]     # grabs the column name
			\s+ (?P<type>\S+) \s+     # grabs the type
		''',re.VERBOSE)
	re_GetRow = re.compile(r'''
			[(]
			(?P<row>.+)
			[)]
			#[(] (?P<row>[^)]+) [)]   # matches everything in parens
		''',re.VERBOSE)
	def __init__(self, packageName=None, className="DefaultJavaClassName",
					baseClass=None, baseClassHeader=None):
		self.tables = list()
		self.packageName = packageName
		self.className = className
		self.baseClass = baseClass
		self.baseClassHeader = baseClassHeader
	def findTableName(self, tableName):
		for i in range(0, len(self.tables)):
			if tableName == self.tables[i].name:
				return i
		return None
	def addTable(self, table):
		self.tables.append(table)
		
	def createStr_Header(self):
		ret = "/*\n"
		ret += _autogenScriptInfo_Str() +"\n"
		ret += "*/\n\n"
		ret += "package "+ self.packageName +";\n"
		ret += "\n"
		if self.baseClassHeader:
			ret += "import "+ self.baseClassHeader +";\n"
		ret += "\n"
		ret += "public final class "+ self.className +" {\n"
		ret += "\tpublic "+ self.className +"() {}"
		return ret
	def createStr_Footer(self):
		ret = "}"
		return ret
	def createStr_Classes(self):
		s = ""
		for table in self.tables:
			s += table.createStr_Class(self.baseClass) +"\n\n"
		return s[0:-2]
	def createStr_DropStr(self):
		s = "public static final String[] SQL_DELETE_ENTRIES = {\n"
		for table in self.tables:
			tmp = "\""+ table.createStr_DropStr() +"\""
			s += indent(tmp) +",\n"
		return s[0:-2] +"\n};"
	def createStr_CreateStr(self):
		s = "public static final String[] SQL_CREATE_ENTRIES = {\n"
		for table in self.tables:
			s += indent( toJavaString(table.createStr_CreateStr()))
			s += ",\n\n"
			tmp = table.createStr_InsertStr()
			if tmp:
				s += indent( toJavaString(tmp))
				s += ",\n\n"
		return s[0:-3] +"\n};"
	def createStr_JavaSqLite(self):
		s = ""
		s += self.createStr_Header() +"\n"
		s += "\n"
		s += indent(self.createStr_Classes()) +"\n"
		s += "\n"
		s += indent(self.createStr_CreateStr()) +"\n"
		s += "\n"
		s += indent(self.createStr_DropStr()) +"\n"
		s += "\n"
		s += self.createStr_Footer()
		return s
		
	def _parseStatement_Create(self, statement):
		match = self.re_CreateStatement.search(statement)
		if match:
			table = SqlTable( match.group('tablename') )
			for ln in match.group('body').split(','):
				match = self.re_GetColumn.search(ln)
				if match:
					name = match.group('name')
					type = match.group('type')
					
					if re.search("unsigned",ln): unsigned = True
					else: unsigned = False
						
					if re.search("NOT NULL",ln): nullVal = False
					else: nullVal = False
						
					if re.search("AUTO_INCREMENT",ln): autoInc = True
					else: autoInc = False
					
					match = re.search("DEFAULT\s+(?P<val>\S+)",ln)
					if match: default = match.group('val')
					else: default=None
					
					table.addColumn( SqlColumn(columnName=name, columnType=type,
									isPrimary=False, defaultVal=default,
									nullValid=nullVal, autoIncrement=autoInc,
									isUnsigned=unsigned) )
					
				if re.search("PRIMARY\s+KEY",ln):
					primaryKey = re.search("PRIMARY\s+KEY\s+[(][`](?P<key>\w+)[`][)]",ln).group('key')
					for column in table.columns:
						if column.name == primaryKey:
							column.primary = True
					
			self.addTable(table)
	def _parseStatement_Insert(self, statement):
		match = self.re_InsertStatement.search(statement)
		if match:
			tableName = match.group('tablename')
			colNames = match.group('colnames')
			body = match.group('body')
			i_table = self.findTableName(tableName)
			mapping = self.tables[i_table].getColumnMapping_csv(colNames)
			for row in self.re_GetRow.findall( body ):
				self.tables[i_table].addRow(row)

	def readFile(self, filename, verbose=False):
		f = open(filename,'r')
		if verbose: print("Reading from \'"+ str(f.name) +"\' in mode \'"+ str(f.mode) +"\'")
		for ln in f.read().split(';'):
			ln += ';'
			if self.re_CreateStatement.search(ln):
				self._parseStatement_Create(ln)
			elif self.re_InsertStatement.search(ln):
				self._parseStatement_Insert(ln)
		f.close()
	def writeJavaSqLiteFile(self, filename, verbose=False):
		f = open(filename,'w')
		if verbose: print("Writing to \'"+ str(f.name) +"\' in mode \'"+ str(f.mode) +"\'")
		f.write( self.createStr_JavaSqLite() )
		f.close()

	def printCreates(self):
		for table in self.tables:
			print( table.createStr_CreateStr() +"\n")
	def printInserts(self):
		for table in self.tables:
			print( table.createStr_InsertStr() +"\n")

#===============================================================================
# init_args()
#	Sets up the command line parsing logic. Any changes to cmd line input should
#	take place here.
#	------------------------------------------
# return
#	args : the list of parsed arguments
#===============================================================================
def init_args():
	parser = argparse.ArgumentParser(description=_description)

	parser.add_argument('-i','--infile',dest='infilename',required=False,
		help='The .sql file that you want to parse from')
	parser.add_argument('-o','--outfile',dest='outfilename',required=False,
		help='The Java file you want to write out to')
	parser.add_argument('--classname','-cn',required=False,
		help='The name of the Java class')
	parser.add_argument('--packagename','-pn',required=False,
		help='The database package to use')
	parser.add_argument('--baseclass','-bc',required=False,
		help='The class that all of the generated classes will implement')
	parser.add_argument('--baseclassHeader','-bch',required=False,
		help='The file that needs to be imported to use the baseclass')

	parser.set_defaults( infilename='FRC_Scouting_Server/scouting.sql',
							outfilename='src/org/frc836/database/FRCScoutingContract.java',
							packagename='org.frc836.database',
							classname='FRCScoutingContract',
							baseclass='BaseColumns',
							baseclassHeader='android.provider.BaseColumns'
						)

	args = parser.parse_args()
	return args

if __name__ == "__main__":
	args = init_args()
	
	SqlCreator = SqlToJava(packageName = args.packagename,
							className = args.classname,
							baseClass = args.baseclass,
							baseClassHeader = args.baseclassHeader
						)
	SqlCreator.readFile(args.infilename, verbose=True)
	SqlCreator.writeJavaSqLiteFile(args.outfilename, verbose=True)
