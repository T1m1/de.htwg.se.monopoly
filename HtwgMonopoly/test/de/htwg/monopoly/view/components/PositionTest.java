package de.htwg.monopoly.view.components;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.util.IMonopolyUtil;

public class PositionTest {

	private static final int STREET = 0;
	private static final int NUMBER_OF_FIELDS = 8;
	private static final int DIFFERENC = 80;
	private static final int EAST = 90;
	private static final int SOUTH = 180;
	private static final int WEST = 270;

	private Position pos;

	@Before
	public void setUp() throws Exception {
		pos = new Position(STREET, NUMBER_OF_FIELDS, DIFFERENC);
	}

	@Test
	public void testEAST() {
		int street = NUMBER_OF_FIELDS + 2;
		Position east = new Position(street, NUMBER_OF_FIELDS, DIFFERENC);
		assertEquals((NUMBER_OF_FIELDS - 1) * DIFFERENC, east.getPictureX());
		assertEquals(east.getY() + 1, east.getPictureY());

		assertEquals(NUMBER_OF_FIELDS * DIFFERENC - IMonopolyUtil.COLORSIZE,
				east.getX());
		assertEquals(street % (NUMBER_OF_FIELDS - 1) * DIFFERENC, east.getY());

		assertEquals(IMonopolyUtil.COLORSIZE, east.getWith());
		assertEquals(DIFFERENC, east.getHigh());
		assertEquals(east.getX() - IMonopolyUtil.COLORSIZE * 2,
				east.getStringX());
		assertEquals(east.getY() + IMonopolyUtil.COLORSIZE, east.getStringY());
		assertEquals(EAST, east.getRotate());

	}

	@Test
	public void testSOUTH() {
		int street = NUMBER_OF_FIELDS * 2 + 2;
		Position east = new Position(street, NUMBER_OF_FIELDS, DIFFERENC);

		assertEquals(NUMBER_OF_FIELDS * DIFFERENC - IMonopolyUtil.COLORSIZE,
				east.getY());
		assertEquals((NUMBER_OF_FIELDS * DIFFERENC)
				- ((street % (NUMBER_OF_FIELDS - 1) + 1) * DIFFERENC),
				east.getX());
		assertEquals(DIFFERENC, east.getWith());

		assertEquals(IMonopolyUtil.COLORSIZE, east.getHigh());

		assertEquals(
				(((NUMBER_OF_FIELDS - 1) * DIFFERENC) - ((street % (NUMBER_OF_FIELDS - 1)) * DIFFERENC)),
				east.getPictureX());
		assertEquals((NUMBER_OF_FIELDS - 1) * DIFFERENC, east.getPictureY());

		assertEquals(east.getX() + DIFFERENC / 5, east.getStringX());

		assertEquals(east.getY() - IMonopolyUtil.COLORSIZE * 3 / 2,
				east.getStringY());
		assertEquals(SOUTH, east.getRotate());

	}

	@Test
	public void testWEST() {
		int street = NUMBER_OF_FIELDS * 3 + 2;
		Position east = new Position(street, NUMBER_OF_FIELDS, DIFFERENC);

		assertEquals(((NUMBER_OF_FIELDS - 1) * DIFFERENC)
				- (street % (NUMBER_OF_FIELDS - 1)) * DIFFERENC, east.getY());

		assertEquals(0, east.getX());

		assertEquals(IMonopolyUtil.COLORSIZE, east.getWith());

		assertEquals(DIFFERENC, east.getHigh());

		assertEquals(0, east.getPictureX());
		assertEquals(east.getY(), east.getPictureY());

		assertEquals(east.getX() + IMonopolyUtil.COLORSIZE * 3 / 2,
				east.getStringX());

		assertEquals(east.getY() + IMonopolyUtil.COLORSIZE, east.getStringY());
		assertEquals(WEST, east.getRotate());

	}

	@Test
	public void testPosition() {
		pos.getHigh();
		pos.getX();
		pos.getY();
	}

	@Test
	public void testGetPictureX() {
		assertEquals(pos.getX() + 1, pos.getPictureX());
	}

	@Test
	public void testGetPictureY() {
		assertEquals(pos.getY() + 1, pos.getPictureY());
	}

	@Test
	public void testGetX() {
		assertEquals(STREET * DIFFERENC, pos.getX());
	}

	@Test
	public void testGetY() {
		assertEquals(0, pos.getY());
	}

	@Test
	public void testGetWith() {
		assertEquals(DIFFERENC, pos.getWith());
	}

	@Test
	public void testGetHigh() {
		assertEquals(IMonopolyUtil.COLORSIZE, pos.getHigh());
	}

	@Test
	public void testGetStringX() {
		assertEquals(pos.getX() + DIFFERENC / 5, pos.getStringX());
	}

	@Test
	public void testGetStringY() {
		assertEquals(pos.getY() + IMonopolyUtil.COLORSIZE * 2, pos.getStringY());
	}

	@Test
	public void testGetRotate() {
		assertEquals(0, pos.getRotate());
	}

}
