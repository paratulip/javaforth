package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.DoublyLinkedList;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;

public class CompiledWord extends ForthWordBase {
	private DoublyLinkedList<ForthWordBase> words = new DoublyLinkedList<>();
	public CompiledWord(String name, String description) {
		super(name, description);
	}
	public CompiledWord() {
		super(null, null);
	}
	public void build(StreamTokenizer tokenizer, ForthDictionary dictionary) throws IOException {
		ForthWordBase nextWord = ControlWordUtility.buildNext(tokenizer, dictionary);
		while (nextWord != null) {
			this.words.addLast(nextWord);
			nextWord = ControlWordUtility.buildNext(tokenizer, dictionary);
		}
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary) {
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		for (ForthWordBase word : this.words) {
			word.execute(stack, dictionary);
			word.execute(stack, dictionary, terminalOutput);
		}
	}
}
