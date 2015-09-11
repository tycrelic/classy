package classfile.annotation;

import classfile.*;
/**
4.8.17 The RuntimeInvisibleAnnotations attribute
The RuntimeInvisibleAnnotations attribute is similar to the RuntimeVisibleAnnotations
attribute, except that the annotations represented by a RuntimeInvisibleAnnotations
attribute must not be made available for return by
reflective APIs, unless the the JVM has been instructed to retain these annotations
via some implementation-specific mechanism such as a command line flag. In the
absence of such instructions, the JVM ignores this attribute.
The RuntimeInvisibleAnnotations attribute is a variable length attribute in the
attributes table of the ClassFile, field_info, and method_info structures. The
RuntimeInvisibleAnnotations attribute records runtime-invisible Java programming
language annotations on the corresponding class, method, or field. Each
ClassFile, field_info, and method_info structure may contain at most one
RuntimeInvisibleAnnotations attribute, which records all the runtime-invisible
Java programming language annotations on the corresponding program element.
The RuntimeInvisibleAnnotations attribute has the following format:
RuntimeInvisibleAnnotations_attribute {
u2 attribute_name_index;
u4 attribute_length;
u2 num_annotations;
annotation annotations[num_annotations];
}
The items of the RuntimeInvisibleAnnotations structure are as follows:
attribute_name_index
The value of the attribute_name_index item must be a valid index into the
constant_pool table. The constant_pool entry at that index must be a
CONSTANT_Utf8_info structure representing the string "RuntimeInvisibleAnnotations".
attribute_length
The value of the attribute_length item indicates the length of the attribute,
excluding the initial six bytes. The value of the attribute_length item is thus
dependent on the number of runtime-invisible annotations represented by the structure,
and their values.
 */
public class RuntimeInvisibleAnnotationsAttribute extends AttributeInfo {
	//public short attributeNameIndex;
	//public int attributeLength;
/**
    The value of the num_annotations item gives the number of runtime-invisible
annotations represented by the structure. Note that a maximum of 65535 runtimeinvisible
Java programming language annotations may be directly attached to a program
element.
  */
    public short numAnnotations;

/**
Each value of the annotations table represents a single runtime-invisible annotation
on a program element.
 */
    public Annotation annotations;//[num_annotations];
}