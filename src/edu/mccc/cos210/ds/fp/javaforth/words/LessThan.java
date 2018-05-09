package edu.mccc.cos210.ds.fp.javaforth.words;

import java.util.NoSuchElementException;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class LessThan extends ForthWordBase {
	public LessThan() {
		super("<", "n1 n2 -- flag True if n1 is less than n2.", false);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict) {
		try {
			Double n1 = (Double) stack.pop();
			Double n2 = (Double) stack.pop();
			if (n1 < n2) {
				stack.push(1);
			}else {
				stack.push(0);
			}
		}catch(NoSuchElementException e) {
			throw new RuntimeException("Stack underflow error on word <");
		}catch(ClassCastException e) {
			throw new RuntimeException("Typing error on word <");
		}catch(Exception e) {
			throw new RuntimeException("Error on word <");
		}
	}
}