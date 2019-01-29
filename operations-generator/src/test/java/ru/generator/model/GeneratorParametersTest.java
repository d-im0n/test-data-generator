package ru.generator.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.generator.exception.GeneratorException;

import static org.junit.Assert.*;

public class GeneratorParametersTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testNullInitialValue() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Number of arguments is incorrect");

        GeneratorParameters parameters = new GeneratorParameters(null);
        parameters.parse();
    }

    @Test
    public void testIncorrectArgumentsQuantity() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Number of arguments is incorrect");

        GeneratorParameters parameters = new GeneratorParameters(new String[]{""});
        parameters.parse();
    }

    @Test
    public void testParsingCorrectArgumentsValues() {
        GeneratorParameters parameters = new GeneratorParameters(new String[]{"offices.txt", "90000", "operations.txt"});
        parameters.parse();

        assertEquals("offices.txt", parameters.getOfficesFileName());
        assertEquals(90000, parameters.getOperationCount());
        assertEquals("operations.txt", parameters.getTargetFileName());
    }

    @Test
    public void testIncorrectOperationCountArgument() {
        exceptionRule.expect(GeneratorException.class);
        exceptionRule.expectMessage("Operation count value is incorrect");

        GeneratorParameters parameters = new GeneratorParameters(new String[]{"offices.txt", "wrong number", "operations.txt"});
        parameters.parse();
    }
}