package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.io.StreamTokenizer;
import java.io.StringReader;
import edu.mccc.cos210.ds.SinglyLinkedList;
import edu.mccc.cos210.ds.fp.javaforth.controlwords.CompiledWord;
import edu.mccc.cos210.ds.fp.javaforth.util.IStackUpdatedEventListener;

public class ForthMachine {
	SinglyLinkedList<ITerminalUpdatedEventListener> terminalUpdatedEventListener = new SinglyLinkedList<>();
	SinglyLinkedList<IStackUpdatedEventListener> stackUpdatedEventListeners = new SinglyLinkedList<>();
	public void AddDictionaryUpdatedEventListener(IDictionaryUpdatedEventListener listener) {
		this.dictionary.addDictionaryUpdatedEventListener(listener);
	}
	public void AddStackUpdatedEventListener(IStackUpdatedEventListener listener) {
		this.stackUpdatedEventListeners.addFirst(listener);
		this.stack.addStackUpdatedEventListener(listener);
	}
	public void AddTerminalUpdatedEventListener(ITerminalUpdatedEventListener listener) {
		this.terminalUpdatedEventListener.addFirst(listener);
	}
	private ObservableStack stack = new ObservableStack();
	private ForthDictionary dictionary = new ForthDictionary();
	private Object executingLock = new Object();
	private volatile boolean stopRequested = false;
	private boolean pauseRequested;
	public ForthMachine() {
		this.dictionary.initRequiredWords();
		this.dictionary.initGraphicsWords();
	}
	/**
	 * Passes the input into the input stream so the interpreter can pick it up. Passing an empty string or no
	 * string will result in interpretation being attempted anyway, which effectively resumes the program if it
	 * was paused.
	 * @param String - input the string of input data to be passed into the inputStream.
	 */
	public void interpret(String input) {
		input = input.trim();
		StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(input));
		tokenizer.resetSyntax();
		tokenizer.wordChars('!', 'z');
		new Thread(() -> {
			synchronized (executingLock) {
				try {
					CompiledWord word = new CompiledWord();
					word.build(tokenizer, dictionary);
					word.execute(stack, dictionary, s -> this.updateTerminal(s));
				} catch (Exception ex) {
					this.terminalUpdatedEventListener.iterator().forEachRemaining(l -> l.onTerminalUpdated(false, ex.getMessage()));
				} finally {
					// TODO: CanExecuteChanged event.
				}
			}
		}).run();
	}
	private void updateTerminal(String s) {
		this.terminalUpdatedEventListener.iterator().forEachRemaining(l -> l.onTerminalUpdated(false, s));
	}
	/**
	 * Method that stops the interpreter from continuing and clears out the data stack, return stack, and 
	 * input stream. The program cannot resume after this method is called.
	 * @return boolean true when the halt was successful, false when it was not.
	 */
	public boolean halt() {
		return true;
	}
	/**
	 * Method that pauses the interpreter, but does not empty the input stream or in any other way disrupt the 
	 * state of the machine, making it possible to resume.
	 * @return boolean true when the halt was successful, false when it was not.
	 */
	public boolean pause() {
		return true;
	}
	public void unpause() {
		this.unpause();
	}
	/**
	 * Method for retrieving the data stack as a string. The stack contains only byte objects, so this string
	 * will be formatted into two digit hexadecimal numbers (0x00 - 0xFF) separated by spaces.
	 * @return String
	 */
	public Iterable<String> getStackAsString() {
		throw new UnsupportedOperationException();
	}
	/**
	 * Method retrieves each variable declared in the 
	 * @return Map<String,String>
	 */
	public edu.mccc.cos210.ds.Map<String, String> getDictionaryAsMap() {
		throw new UnsupportedOperationException();
	}
	private void reset() {
		this.stopRequested = false;
		this.pauseRequested = false;
//		this.stack = new ObservableStack();
//		for (IStackUpdatedEventListener listener : this.stackUpdatedEventListeners) {
//			this.stack.addStackUpdatedEventListener(listener);
//		}
//		this.dictionary = new ForthDictionary();
	}
}
