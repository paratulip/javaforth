package edu.mccc.cos210.ds.fp.javaforth.words;

import java.util.NoSuchElementException;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class ZeroEquals extends ForthWordBase {
	public ZeroEquals() {
		super("0=", "n -- flag True if n is zero (negative)", false);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict) {
		try {
			Double n1 = (Double) stack.pop();
			if (n1 == 0) {
				stack.push(1);
			}else {
				stack.push(0);
			}
		}catch(NoSuchElementException e) {
			throw new RuntimeException("Stack underflow error on word 0=");
		}catch(ClassCastException e) {
			throw new RuntimeException("Typing error on word 0=");
		}catch(Exception e) {
			throw new RuntimeException("Unknown error on word 0=");
		}
	}
}