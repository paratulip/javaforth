package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;

public abstract class ForthWordBase {
	private String name;
	private String description;
	public String getDescription() {
		return description;
	}
	public ForthWordBase(String name, String description, boolean immediate) {
		super();
		this.name = name;
		this.description = description;
		this.immediate = immediate;
	}
	public String getName() {
		return name;
	}
	public boolean isImmediate() {
		return immediate;
	}
	private boolean immediate;
	public abstract void execute(IStack<Object> stack, ForthDictionary dictionary);
	/* 
	 * override this if you need to output to terminal.
	 */
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
	}
}
