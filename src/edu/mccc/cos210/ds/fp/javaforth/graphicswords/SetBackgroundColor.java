package edu.mccc.cos210.ds.fp.javaforth.graphicswords;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.lang.reflect.InvocationTargetException;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.GraphicsWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;
import edu.mccc.cos210.ds.fp.javaforth.viewIde.GraphicsFrame;

public class SetBackgroundColor extends GraphicsWordBase {
	public SetBackgroundColor(GraphicsFrame frame) {
		super("SETBACKGROUNDCOLOR", "r g b -- Sets background color of graphics frame", frame);
	}
	@Override
	public void draw(Graphics2D g2d) {
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		try {
			EventQueue.invokeAndWait(()->{
				int b = (int)(double)stack.pop();
				int g = (int)(double)stack.pop();
				int r = (int)(double)stack.pop();
				super.getFrame().setBackground(new Color(r,g,b));
			});
		} catch (InvocationTargetException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}