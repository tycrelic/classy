package classfile.attribute;

import classfile.*;
/**
4.8.11 The SourceDebugExtension Attribute
The SourceDebugExtension attribute is an optional attribute in the attributes table
of the ClassFile (§4.2) structure. There can be no more than one SourceDebugExtension
attribute in the attributes table of a given ClassFile structure.
The SourceDebugExtension attribute has the following format:
SourceDebugExtension_attribute {
u2 attribute_name_index;
u4 attribute_length;
u1 debug_extension[attribute_length];
}
The items of the SourceDebugExtension_attribute structure are as follows:
attribute_name_index
The value of the attribute_name_index item must be a valid
index into the constant_pool table. The constant_pool entry at
that index must be a CONSTANT_Utf8_info (§4.5.7) structure
representing the string "SourceDebugExtension".
attribute_length
The value of the attribute_length item indicates the length of
the attribute, excluding the initial six bytes. The value of the
attribute_length item is thus the number of bytes in the
debug_extension[] item.
debug_extension[]
The debug_extension array holds a string, which must be in
UTF-8 format. There is no terminating zero byte.The string in the
debug_extension item will be interpreted as extended
debugging information. The content of this string has no semantic
effect on the Java Virtual Machine.
 */
public class SourceDebugExtensionAttribute extends AttributeInfo {
	//public short attributeNameIndex;
	//public int attributeLength;
    /**
The debug_extension array holds a string, which must be in
UTF-8 format. There is no terminating zero byte.The string in the
debug_extension item will be interpreted as extended
debugging information. The content of this string has no semantic
effect on the Java Virtual Machine.
     */
    byte[] debugExtension;
}