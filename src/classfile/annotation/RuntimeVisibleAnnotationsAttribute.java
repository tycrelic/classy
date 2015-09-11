package classfile.annotation;

import classfile.*;

/**
4.8.16 The RuntimeVisibleAnnotations attribute
The RuntimeVisibleAnnotations attribute is a variable length attribute in the
attributes table of the ClassFile, field_info, and method_info structures. The
RuntimeVisibleAnnotations attribute records runtime-visible Java programming
language annotations on the corresponding class, method, or field. Each
ClassFile, field_info, and method_info structure may contain at most one
RuntimeVisibleAnnotations attribute, which records all the runtime-visible
Java programming language annotations on the corresponding program element.
The JVM must make these annotations available so they can be returned by the
appropriate reflective APIs.
The RuntimeVisibleAnnotations attribute has the following format:
RuntimeVisibleAnnotations_attribute {
u2 attribute_name_index;
u4 attribute_length;
u2 num_annotations;
annotation annotations[num_annotations];
}

The items of the RuntimeVisibleAnnotations structure are as follows:
attribute_name_index
The value of the attribute_name_index item must be a valid index into the
constant_pool table. The constant_pool entry at that index must be a
CONSTANT_Utf8_info structure representing the string "RuntimeVisibleAnnotations".
attribute_length
The value of the attribute_length item indicates the length of the attribute,
excluding the initial six bytes. The value of the attribute_length item is thus
dependent on the number of runtime-visible annotations represented by the structure,
and their values.
 */
public class RuntimeVisibleAnnotationsAttribute extends AttributeInfo {
    /**
The value of the num_annotations item gives the number of runtime-visible
annotations represented by the structure. Note that a maximum of 65535 runtimevisible
Java programming language annotations may be directly attached to a program
element.
     */
    public short numAnnotations;

    /**
Each value of the annotations table represents a single runtime-visible annotation
on a program element.
     */
    public Annotation annotations;//[num_annotations];
}
