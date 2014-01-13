package de.htwg.monopoly.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.entities.impl.Bank;
import de.htwg.monopoly.entities.impl.FieldObject;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.entities.impl.Street;

public class BankTest {
	
	Player testPlayer;
	Player testOwner;
	Street testField;
	IFieldObject testNotStreet;

	@Before
	public void setUp() throws Exception {
		testField = new Street("foo", 1000, null, 50, 20);
		testPlayer = new Player("bar", 'a', 50);
		testOwner = new Player("unicorn", 'b', 0);
		testNotStreet = new FieldObject("Not a Street", 'l', 0);
		testField.setOwner(testOwner);
	}

	@Test
	public void testPayRent() {
		Bank.payRent(testPlayer, testField);
		assertEquals(0, testPlayer.getBudget());
		assertEquals(50, testOwner.getBudget());
	}
	
	@Test(expected = AssertionError.class)
	public void testPayRent2() {
		Bank.payRent(testPlayer, testNotStreet);
	}

	@Test
	public void testReceiveMoney() {
		Bank.receiveMoney(testPlayer, 200);
		assertEquals(250, testPlayer.getBudget());
	}
	
	@Test
	public void testGetParkingMoney() {
		Bank.addParkingMoney(100);
		assertEquals(100, Bank.getParkingMoney());
		Bank.addParkingMoney(100);
		assertEquals(100, Bank.getParkingMoney());
	}
	
	

}
