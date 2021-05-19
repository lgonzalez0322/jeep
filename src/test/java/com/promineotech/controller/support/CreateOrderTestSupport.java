package com.promineotech.controller.support;

public class CreateOrderTestSupport extends BaseTest{
	
	
	/**
	 * 
	 * @return  this will return selection of basic standards
	 */
	protected String createorderbody()  {
	//@formatter:off
	return  "{\n"
			+ "		\"customer\":\"MORRISON_LINA\",\n"
			+ "		\"model\":\"WRANGLER\",\n"
			+ "		\"trim\":\"Sport Altitude\",\n"
			+ "		\"doors\":\"4\",\n"
			+ "		\"color\":\"EXT_NACHO\",\n"
			+ "		\"engine\":\"2_0_TURBO\",\n"
			+ " 	\"tire\":\"35_TOYO\",\n"
			+ "		 \"options\":[\n"
			+ "			\"DOOR_QUAD_4\",\n"
			+ "			\"EXT_AEV_LIFT\",\n"
			+ "			\"EXT_WARN_WINCH\",\n"
			+ "			\"EXT_WARN_BUMPER_FRONT\",\n"
			+ "			\"EXT_WARN_BUMPER_REAR\",\n"
			+ "			\"EXT_ARB_COMPRESSOR\"\n"
			+ "		]\n"
			+ "}";
			//@formatter:on
}
}
