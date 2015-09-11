package classfile.annotation;

public class ConstValue extends ElementValue {
    /**
The const_value_index item is used if the tag item is one of 'B', 'C', 'D', 'F',
'I', 'J', 'S', 'Z', or 's'. The value of the const_value_index item must be a valid
index into the constant_pool table. The constant_pool entry at that index must
be of the correct entry type for the field type designated by the tag item, as specified
in Table 4.8.
     */
    short constValueIndex;

}
