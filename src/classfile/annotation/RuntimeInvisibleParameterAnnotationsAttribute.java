package classfile.annotation;

import classfile.*;
/**
4.8.19 The RuntimeInvisibleParameterAnnotations attribute
The RuntimeInvisibleParameterAnnotations attribute is similar to the RuntimeVisibleParameterAnnotations
attribute, except that the annotations represented
by a RuntimeInvisibleParameterAnnotations attribute must not be
made available for return by reflective APIs, unless the the JVM has specifically
been instructed to retain these annotations via some implementation-specific mechanism
such as a command line flag. In the absence of such instructions, the JVM
ignores this attribute.
The RuntimeInvisibleParameterAnnotations attribute is a variable length
attribute in the attributes table of the method_info structure. The RuntimeInvisibleParameterAnnotations
attribute records runtime-invisible Java programming
language annotations on the parameters of the corresponding method. Each
method_info structure may contain at most one RuntimeInvisibleParameter-
Annotations attribute, which records all the runtime-invisible Java programming
language annotations on the parameters of the corresponding method.
The RuntimeInvisibleParameterAnnotations attribute has the following format:
RuntimeInvisibleParameterAnnotations_attribute {
u2 attribute_name_index;
u4 attribute_length;
u1 num_parameters;
{
u2 num_annotations;
annotation annotations[num_annotations];
} parameter_annotations[num_parameters];
}
The items of the RuntimeInvisibleParameterAnnotations structure are as follows:
attribute_name_index
The value of the attribute_name_index item must be a valid index into the
constant_pool table. The constant_pool entry at that index must be a
CONSTANT_Utf8_info structure representing the string "RuntimeInvisibleParameterAnnotations".
attribute_length
The value of the attribute_length item indicates the length of the attribute,
excluding the initial six bytes. The value of the attribute_length item is thus
dependent on the number of parameters, the number of runtime-invisible annotations
on each parameter, and their values.
 */
public class RuntimeInvisibleParameterAnnotationsAttribute extends AttributeInfo {
	//public short attributeNameIndex;
	//public int attributeLength;

        /**
The value of the num_parameters item gives the number of parameters of the
method represented by the method_info structure on which the annotation occurs.
(This duplicates information that could be extracted from the method descriptor.)
         */
    public short numParameters;
            /**
Each value of the parameter_annotations table represents all of the runtimeinvisible
annotations on a single parameter. The sequence of values in the table corresponds
to the sequence of parameters in the method signature.
             */
    public ParameterAnnotations parameterAnnotations;
    /**
Each
parameter_annotations entry contains the following two items:
    */
    public static class ParameterAnnotations {
        /**
The value of the num_annotations item indicates the number of runtimeinvisible
annotations on the parameter corresponding to the sequence number of this
parameter_annotations element.
        */
        public short numAnnotations;
        /**
Each value of the annotations table represents a single runtime-invisible
annotation on the parameter corresponding to the sequence number of this
parameter_annotations element.
         */
        public Annotation[] annotations;
    }
}