package classfile.annotation;

import classfile.*;
/**
4.8.18 The RuntimeVisibleParameterAnnotations attribute
The RuntimeVisibleParameterAnnotations attribute is a variable length
attribute in the attributes table of the method_info structure. The RuntimeVisibleParameterAnnotations
attribute records runtime-visible Java programming
language annotations on the parameters of the corresponding method. Each
method_info structure may contain at most one RuntimeVisibleParameterAnnotations
attribute, which records all the runtime-visible Java programming language
annotations on the parameters of the corresponding method. The JVM must
make these annotations available so they can be returned by the appropriate reflective
APIs.
The RuntimeVisibleParameterAnnotations attribute has the following format:
RuntimeVisibleParameterAnnotations_attribute {
u2 attribute_name_index;
u4 attribute_length;
u1 num_parameters;
{
u2 num_annotations;
annotation annotations[num_annotations];
} parameter_annotations[num_parameters];
}
The items of the RuntimeVisibleParameterAnnotations structure are as follows:
attribute_name_index
The value of the attribute_name_index item must be a valid index into the
constant_pool table. The constant_pool entry at that index must be a
CONSTANT_Utf8_info structure representing the string "RuntimeVisibleParameterAnnotations".
attribute_length
The value of the attribute_length item indicates the length of the attribute,
excluding the initial six bytes. The value of the attribute_length item is thus
dependent on the number of parameters, the number of runtime-visible annotations
on each parameter, and their values.
 */
public class RuntimeVisibleParameterAnnotationsAttribute extends AttributeInfo {
	//public short attributeNameIndex;
	//public int attributeLength;

        /**
The value of the num_parameters item gives the number of parameters of the
method represented by the method_info structure on which the annotation occurs.
(This duplicates information that could be extracted from the method descriptor.)
         */
    public short numParameters;
            /**
Each value of the parameter_annotations table represents all of the runtimevisible
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
The value of the num_annotations item indicates the number of runtime-visible
annotations on the parameter corresponding to the sequence number of this
parameter_annotations element.
        */
        public short numAnnotations;
        /**
        Each value of the annotations table represents a single runtime-visible annotation
on the parameter corresponding to the sequence number of this
parameter_annotations element.
         */
        public Annotation[] annotations;
    }
}