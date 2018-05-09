package edu.mccc.cos210.ds.fp.javaforth.words;

import java.util.NoSuchElementException;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Or extends ForthWordBase {
	public Or() {
		super("OR", "n1 n2 -- n3 Leave the bitwise inclusive-or of two numbers." , false);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict) {
		try {
			int n1 = ((Double) stack.pop()).intValue();
			int n2 = ((Double) stack.pop()).intValue();
			stack.push(n1 | n2);
		}catch(NoSuchElementException e){
			throw new RuntimeException("Stack underflow error on word OR.");
		}
	}
}