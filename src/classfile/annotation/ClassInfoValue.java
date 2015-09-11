package classfile.annotation;

/**

 */
public class ClassInfoValue extends ElementValue {
    /**
The class_info_index item is used if the tag item is 'c'. The
class_info_index item must be a valid index into the constant_pool table. The
constant_pool entry at that index must be a CONSTANT_Utf8_info structure representing
the return descriptor (§4.4.3) of the type that is reified by the class represented
by this element_value structure (e.g., ‘V’ for Void.class, ‘Ljava/lang/
Object;’ for Object, etc.)
     */
    short classInfoIndex;

}
