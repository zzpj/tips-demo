package com.example.reflection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Reflection showroom")
public class ReflectionTest {

    @Test
    void shouldGetFieldNames() {

        Dog dog = new Dog("Pluto", true);
        Field[] declaredFields = dog.getClass().getDeclaredFields();

        assertTrue(Arrays.asList("name", "domesticated")
                .containsAll(Arrays.stream(declaredFields).map(Field::getName).toList())
        );
    }

    @Test
    void shouldGiveClassNameFromCreatedObject() {
        Dog dog = new Dog("Pluto", true);
        Class<? extends Dog> clazz = dog.getClass();

        assertEquals("Dog", clazz.getSimpleName());
        assertEquals("com.example.reflection.Dog", clazz.getName());
        assertEquals("com.example.reflection.Dog", clazz.getCanonicalName());
    }

    @Test
    void shouldGiveClassNameFromJavaApi() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.example.reflection.Dog");

        assertEquals("Dog", clazz.getSimpleName());
        assertEquals("com.example.reflection.Dog", clazz.getName());
        assertEquals("com.example.reflection.Dog", clazz.getCanonicalName());
    }

    @Test
    void shouldThrowsExceptionForNonExistingClass() {
        assertThrows(ClassNotFoundException.class, () -> Class.forName("com.foo.bar"));
    }

    @Test
    void shouldRecognizedClassModifiers() throws ClassNotFoundException {
        Class<?> dogClazz = Class.forName("com.example.reflection.Dog");
        Class<?> animalClazz = Class.forName("com.example.reflection.Animal");
        Class<?> motionInterface = Class.forName("com.example.reflection.Motion");

        int goatMods = dogClazz.getModifiers();
        int animalMods = animalClazz.getModifiers();
        int motionInterfaceModifiers = motionInterface.getModifiers();

        assertTrue(Modifier.isPublic(goatMods));
        assertTrue(Modifier.isAbstract(animalMods));
        assertTrue(Modifier.isPublic(animalMods));

        assertFalse(Modifier.isFinal(animalMods));
        assertFalse(Modifier.isStatic(animalMods));

        assertTrue(Modifier.isInterface(motionInterfaceModifiers));
    }

    @Test
    void shouldGetPackageClassInfo() {
        Dog dog = new Dog("Pluto", true);
        Class<?> dogClass = dog.getClass();
        Package dogClassPackage = dogClass.getPackage();

        assertEquals("com.example.reflection", dogClassPackage.getName());
    }

    @Test
    void shouldGetSuperClassInfo() {
        Dog dog = new Dog("Pluto", true);
        Class<?> dogClass = dog.getClass();
        Class<?> dogSuperClass = dogClass.getSuperclass();

        assertEquals("Animal", dogSuperClass.getSimpleName());

        // this is extra check
        assertEquals("Object", "some funny string".getClass().getSuperclass().getSimpleName());
    }

    @Test
    void shouldGetInterfacesInfo() throws ClassNotFoundException {
        Class<?> sparrowClazz = Class.forName("com.example.reflection.Sparrow");
        Class<?> animalClazz = Class.forName("com.example.reflection.Animal");

        Class<?>[] sparrowInterfaces = sparrowClazz.getInterfaces();
        Class<?>[] animalInterfaces = animalClazz.getInterfaces();

        assertEquals(2, sparrowInterfaces.length);
        assertEquals(0, animalInterfaces.length);
        assertEquals("Flyable", sparrowInterfaces[0].getSimpleName());
        assertEquals("Motion", sparrowInterfaces[1].getSimpleName());
    }

    @Test
    void shouldGetConstructors() throws ClassNotFoundException {
        Class<?> sparrowClazz = Class.forName("com.example.reflection.Sparrow");

        Constructor<?>[] sparrowClazzConstructors = sparrowClazz.getConstructors();

        assertEquals(2, sparrowClazzConstructors.length);
        assertEquals("com.example.reflection.Sparrow", sparrowClazzConstructors[0].getName());
        assertEquals("com.example.reflection.Sparrow", sparrowClazzConstructors[1].getName());
    }

    @Test
    void shouldGetAllClassMethods() throws ClassNotFoundException {
        Class<?> animalClass = Class.forName("com.example.reflection.Animal");
        Method[] methods = animalClass.getDeclaredMethods();
        List<String> actualMethods = Arrays.stream(methods).map(Method::getName).toList();

        assertEquals(2, actualMethods.size());
        assertTrue(actualMethods.containsAll(Arrays.asList("getName", "getSound")));
    }

    @Test
    void shouldGetAllClassFields() throws ClassNotFoundException {
        Class<?> dogClazz = Class.forName("com.example.reflection.Dog");
        Field[] dogClazzDeclaredFields = dogClazz.getDeclaredFields();

        List<String> actualFields = Arrays.stream(dogClazzDeclaredFields).map(Field::getName).toList();

        assertEquals(1, actualFields.size());
        assertTrue(actualFields.contains("domesticated"));
    }

    @DisplayName("Reflection power part 1")
    @Test
    public void shouldInitializeObjectsAtRuntime() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> sparrowClazz = Class.forName("com.example.reflection.Sparrow");
        Class<?> dogClazz = Class.forName("com.example.reflection.Dog");

        Constructor<?> sparrowConstructor1 = sparrowClazz.getConstructor();
        Constructor<?> sparrowConstructor2 = sparrowClazz.getConstructor(String.class);
        Constructor<?> dogConstructor = dogClazz.getConstructor(String.class, boolean.class);

        Sparrow sparrow1 = (Sparrow) sparrowConstructor1.newInstance();
        Sparrow sparrow2 = (Sparrow) sparrowConstructor2.newInstance("Elemelek");
        Dog dog = (Dog) dogConstructor.newInstance("Burek", true);

        assertEquals("NPC Sparrow", sparrow1.getName());
        assertEquals("Elemelek", sparrow2.getName());
        assertEquals("tweets", sparrow1.getSound());
        assertEquals("tweets", sparrow2.getSound());
        assertTrue(sparrow1.canFly());
        assertTrue(sparrow2.canFly());
        assertEquals("Burek", dog.getName());
        assertEquals("walks", dog.getLocomotion());
        assertEquals("barks", dog.getSound());
    }

    @Test
    void shouldGetClassFieldByName() throws ClassNotFoundException, NoSuchFieldException {
        Field field = Class.forName("com.example.reflection.Dog").getDeclaredField("domesticated");
        Class<?> fieldClass = field.getType();
        assertEquals("boolean", fieldClass.getSimpleName());
    }

    @Test
    public void shouldSetAndGetsValueFromClassField() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<?> dogClass = Class.forName("com.example.reflection.Dog");
        Dog dog = (Dog) dogClass.getConstructor(String.class, boolean.class).newInstance("Pluto", true);

        assertEquals("Pluto", dog.getName());
        assertTrue(dog.isDomesticated());

        // get from dog class
        Field booleanDomesticatedField = dogClass.getDeclaredField("domesticated");
        booleanDomesticatedField.setAccessible(true);
        // set dog as not domesticated, it's wolf!
        booleanDomesticatedField.set(dog, false);

        // get from super class
        Field stringNameField = dogClass.getSuperclass().getDeclaredField("name");
        stringNameField.setAccessible(true);
        // set new dog-wolf name
        stringNameField.set(dog, "Wolf!");

        assertFalse(dog.isDomesticated());
        assertEquals("Wolf!", dog.getName());
    }

    @Test
    public void shouldInvokeDeclaredMethodsFromClassField() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<?> dogClass = Class.forName("com.example.reflection.Dog");
        Dog dog = (Dog) dogClass.getConstructor(String.class, boolean.class).newInstance("Pluto", true);

        assertEquals("barks", dog.getSound());

        Method getSoundMethod = dog.getClass().getDeclaredMethod("getSound");
        assertTrue(getSoundMethod.canAccess(dog));

        String soundInvoke = (String) getSoundMethod.invoke(dog);
        assertEquals("barks", soundInvoke);
    }

    @Test
    void shouldBeRecordFriendlyToo() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        assertTrue(Mushroom.class.isRecord());
        assertFalse(Mushroom.class.isInterface());

        RecordComponent[] recordComponents = Mushroom.class.getRecordComponents();
        assertEquals(2, recordComponents.length);
        List<String> fieldNames = Arrays.stream(recordComponents).map(RecordComponent::getName).toList();
        assertTrue(fieldNames.containsAll(Arrays.asList("name", "poisonous")));

        Constructor<Mushroom> constructor = Mushroom.class.getConstructor(String.class, boolean.class);
        Mushroom champignon = constructor.newInstance("champignon", false);
        assertEquals("champignon", champignon.name());
        assertFalse(champignon.poisonous());
    }
}