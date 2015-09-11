package classfile.attribute;

import classfile.*;
/**
4.8.5 The Exceptions Attribute
The Exceptions attribute is a variable-length attribute used in the attributes
table of a method_info (§4.7) structure. The Exceptions attribute indicates which
checked exceptions a method may throw. There may be at most one Exceptions
attribute in each method_info structure.
The Exceptions attribute has the following format:
Exceptions_attribute {
u2 attribute_name_index;
u4 attribute_length;
u2 number_of_exceptions;
u2 exception_index_table[number_of_exceptions];
}
The items of the Exceptions_attribute structure are as follows:
attribute_name_index
The value of the attribute_name_index item must be a valid
index into the constant_pool table. The constant_pool entry
at that index must be the CONSTANT_Utf8_info (§4.5.7)
structure representing the string "Exceptions".
attribute_length
The value of the attribute_length item indicates the attribute
length, excluding the initial six bytes.
number_of_exceptions
The value of the number_of_exceptions item indicates the
number of entries in the exception_index_table.
exception_index_table[]
Each value in the exception_index_table array must be a
valid index into the constant_pool table. The constant_pool
entry referenced by each table item must be a
CONSTANT_Class_info (§4.5.1) structure representing a class
type that this method is declared to throw.
A method should throw an exception only if at least one of the following three
criteria is met:
• The exception is an instance of RuntimeException or one of its subclasses.
• The exception is an instance of Error or one of its subclasses.
• The exception is an instance of one of the exception classes specified in the
exception_index_table just described, or one of their subclasses.
These requirements are not enforced in the Java virtual machine; they are
enforced only at compile time.
 */
public class ExceptionsAttribute extends AttributeInfo {
    /**
The value of the number_of_exceptions item indicates the
number of entries in the exception_index_table.
     */
	public short numberOfExceptions;

        /**
Each value in the exception_index_table array must be a
valid index into the constant_pool table. The constant_pool
entry referenced by each table item must be a
CONSTANT_Class_info (§4.5.1) structure representing a class
type that this method is declared to throw.
         */
	public short[] exceptionIndexTable;//[numberOfExceptions];
}