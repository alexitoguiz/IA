package aima.core.environment.canibals;

import java.util.Arrays;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;



/**
 * @author Fernando Peña Bes (NIA: 756012)
 * @date october 2019
 */
public class CanibalsBoard {

	public static Action M1C = new DynamicAction("Move 1C");

	public static Action M2C = new DynamicAction("Move 2C");

	public static Action M1M = new DynamicAction("Move 1M");

	public static Action M2M = new DynamicAction("Move 2M");
	
	public static Action M1M1C = new DynamicAction("Move 1M 1C");

	private int[] state;
	
	private static final int I = 0;
	private static final int D = 1;

	//
	// PUBLIC METHODS
	//
	// M = misioneros
	// C = canibales
	// B = barca
	// Estado. Los {M izq, C izq, B (0 izq, 1 der), M der, C der} 
	public CanibalsBoard() {
		state = new int[] {3, 3, 0, 0, 0};
	}

	public CanibalsBoard(int[] state) {
		this.state = new int[state.length];
		System.arraycopy(state, 0, this.state, 0, state.length);
	}

	public CanibalsBoard(CanibalsBoard copyBoard) {
		this(copyBoard.getState());
	}

	public int[] getState() {
		return state;
	}

	
	// Acciones
	public void move1C() {
		if(getB() == I) {
			setCI(getCI() - 1);
			setCD(getCD() + 1);
			setB();
		}
		else {
			setCD(getCD() - 1);
			setCI(getCI() + 1);
			setB();
		}
	}

	public void move2C() {
		if(getB() == I) {
			setCI(getCI() - 2);
			setCD(getCD() + 2);
			setB();
		}
		else {
			setCD(getCD() - 2);
			setCI(getCI() + 2);
			setB();
		}
	}

	public void move1M() {
		if(getB() == I) {
			setMI(getMI() - 1);
			setMD(getMD() + 1);
			setB();
		}
		else {
			setMD(getMD() - 1);
			setMI(getMI() + 1);
			setB();
		}
	}

	public void move2M() {
		if(getB() == I) {
			setMI(getMI() - 2);
			setMD(getMD() + 2);
			setB();
		}
		else {
			setMD(getMD() - 2);
			setMI(getMI() + 2);
			setB();
		}
	}
	
	public void move1M1C() {
		if(getB() == I) {
			setMI(getMI() - 1);
			setCI(getCI() - 1);
			setMD(getMD() + 1);
			setCD(getCD() + 1);
			setB();
		}
		else {
			setMD(getMD() - 1);
			setCD(getCD() - 1);
			setMI(getMI() + 1);
			setCI(getCI() + 1);
			setB();
		}
	}

	// Precodiciones para realizar movimientos
	public boolean canMovePerson(Action who) {
		boolean retVal = true;
		int MI = getMI();
		int CI = getCI();
		int MD = getMD();
		int CD = getCD();
		int B = getB();
		
		// B = x
		
		if (who.equals(M1C)) {
						     // Cx >= 1 && (My >= Cy + 1 || My == 0)
			retVal = (B == I && CI >= 1 && (MD >= CD + 1 || MD == 0)) ||
				     (B == D && CD >= 1 && (MI >= CI + 1 || MI == 0));
		}
		
		else if (who.equals(M2C)) {
						     // Cx >= 2 && (My >= Cy + 2 || My == 0)
			retVal = (B == I && CI >= 2 && (MD >= CD + 2 || MD == 0)) ||
					 (B == D && CD >= 2 && (MI >= CI + 2 || MI == 0));
		}
		
		else if (who.equals(M1M)) {
			                 // Mx >= 1 && (Mx >= Cx + 1 || Mx == 1) && My >= Cy - 1
			retVal = (B == I && MI >= 1 && (MI >= CI + 1 || MI == 1) && MD >= CD - 1) ||
				     (B == D && MD >= 1 && (MD >= CD + 1 || MD == 1) && MI >= CI - 1);
		}
		
		else if (who.equals(M2M)) {
			                 // Mx >= 2 && (Mx >= Cx + 2 || Mx == 2) && My >= Cy - 2
			retVal = (B == I && MI >= 2 && (MI >= CI + 2 || MI == 2) && MD >= CD - 2) ||
				     (B == D && MD >= 2 && (MD >= CD + 2 || MD == 2) && MI >= CI - 2);
		}
		
		else if (who.equals(M1M1C)) {
			                 // Mx >= 1 && Cx >= 1 && My >= Cy
			retVal = (B == I && MI >= 1 && CI >= 1 && MD >= CD) ||
				     (B == D && MD >= 1 && CD >= 1 && MI >= CI);
		}
		return retVal;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CanibalsBoard other = (CanibalsBoard) obj;
		if (!Arrays.equals(state, other.state))
			return false;
		return true;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(state);
		return result;
	}

	@Override
	public String toString() {
		String retVal = getMI()  + " " + getCI() + " | " + getB_char() + " | " + getMD() + " " + getCD();
		return retVal;
	}

	//
	// PRIVATE METHODS
	//

	
	// Métodos trabajar con el estado
	// Get
	private int getMI() {
		return state[0];
	}
	
	private int getCI() {
		return state[1];
	}
	
	private int getB() {
		return state[2];
	}
	
	private char getB_char() {
		return (state[2] == 0) ? 'I' : 'D';
	}
	
	private int getMD() {
		return state[3];
	}
	
	private int getCD() {
		return state[4];
	}
	
	
	// Set
	private void setMI(int n) {
		state[0] = n;
	}
	
	private void setCI(int n) {
		state[1] = n;
	}
	
	private void setB() {
		if (state[2] == I) state[2] = D;
		else state[2] = I;
	}
	
	private void setMD(int n) {
		state[3] = n;
	}

	private void setCD(int n) {
		state[4] = n;
	}

}