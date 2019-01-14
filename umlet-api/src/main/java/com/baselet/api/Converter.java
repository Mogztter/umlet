package com.baselet.api;

import com.baselet.control.config.handler.ConfigHandler;
import com.baselet.control.enums.Program;
import com.baselet.control.enums.RuntimeType;
import com.baselet.control.util.Utils;
import com.baselet.diagram.DiagramHandler;
import com.baselet.diagram.io.OutputHandler;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.baselet.control.util.Utils.readBuildInfo;


public class Converter {

  public static void main(String[] args) throws Exception {
    String source = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><umlet_diagram><element><type>com.umlet.element.base.Relation</type><coordinates><x>390</x><y>160</y><w>90</w><h>120</h></coordinates><panel_attributes>lt=-</panel_attributes><additional_attributes>70;100;20;20</additional_attributes></element><element><type>com.umlet.element.base.Relation</type><coordinates><x>390</x><y>240</y><w>90</w><h>80</h></coordinates><panel_attributes>lt=-</panel_attributes><additional_attributes>70;20;20;60</additional_attributes></element><element><type>com.umlet.element.custom.ThreeWayRelation</type><coordinates><x>460</x><y>250</y><w>30</w><h>20</h></coordinates><panel_attributes></panel_attributes><additional_attributes>transparentSelection=false</additional_attributes></element><element><type>com.umlet.element.base.Relation</type><coordinates><x>470</x><y>240</y><w>90</w><h>40</h></coordinates><panel_attributes>lt=-</panel_attributes><additional_attributes>70;20;20;20</additional_attributes></element><element><type>com.umlet.element.base.Relation</type><coordinates><x>360</x><y>160</y><w>40</w><h>160</h></coordinates><panel_attributes>lt=&lt;-\n" +
      "r1=to</panel_attributes><additional_attributes>20;140;20;20</additional_attributes></element><element><type>com.umlet.element.base.Class</type><coordinates><x>310</x><y>300</y><w>100</w><h>30</h></coordinates><panel_attributes>Airport</panel_attributes><additional_attributes>transparentSelection=false</additional_attributes></element><element><type>com.umlet.element.base.Relation</type><coordinates><x>320</x><y>160</y><w>40</w><h>160</h></coordinates><panel_attributes>lt=&lt;-\n" +
      "\n" +
      "r1=from</panel_attributes><additional_attributes>20;140;20;20</additional_attributes></element><element><type>com.umlet.element.base.Class</type><coordinates><x>0</x><y>300</y><w>100</w><h>30</h></coordinates><panel_attributes>MilesAccount</panel_attributes><additional_attributes>transparentSelection=false</additional_attributes></element><element><type>com.umlet.element.base.Relation</type><coordinates><x>400</x><y>150</y><w>180</w><h>40</h></coordinates><panel_attributes>lt=-\n" +
      "m1=1\n" +
      "r2=fh\n" +
      "m2=*</panel_attributes><additional_attributes>20;20;160;20</additional_attributes></element><element><type>com.umlet.element.base.Relation</type><coordinates><x>80</x><y>140</y><w>260</w><h>40</h></coordinates><panel_attributes>lt=-\n" +
      "r1=passagengers\n" +
      "m1=*\n" +
      "r2=flights\n" +
      "m2=*</panel_attributes><additional_attributes>20;20;240;20</additional_attributes></element><element><type>com.umlet.element.base.Class</type><coordinates><x>0</x><y>150</y><w>100</w><h>30</h></coordinates><panel_attributes>Passenger</panel_attributes><additional_attributes>transparentSelection=false</additional_attributes></element><element><type>com.umlet.element.base.Relation</type><coordinates><x>220</x><y>20</y><w>57</w><h>160</h></coordinates><panel_attributes>lt=.\n" +
      "r1=booking</panel_attributes><additional_attributes>28;20;28;140</additional_attributes></element><element><type>com.umlet.element.base.Relation</type><coordinates><x>350</x><y>60</y><w>184</w><h>114</h></coordinates><panel_attributes>lt=-\n" +
      "&lt;connectingFlights\n" +
      "m1=*</panel_attributes><additional_attributes>20;84;20;34;100;34;100;74;70;94</additional_attributes></element><element><type>com.umlet.element.base.Class</type><coordinates><x>560</x><y>150</y><w>110</w><h>30</h></coordinates><panel_attributes>FlightHandling</panel_attributes><additional_attributes>transparentSelection=false</additional_attributes></element><element><type>com.umlet.element.base.Class</type><coordinates><x>540</x><y>240</y><w>100</w><h>30</h></coordinates><panel_attributes>Airline</panel_attributes><additional_attributes>transparentSelection=false</additional_attributes></element><element><type>com.umlet.element.base.Class</type><coordinates><x>320</x><y>150</y><w>100</w><h>30</h></coordinates><panel_attributes>Flight</panel_attributes><additional_attributes>transparentSelection=false</additional_attributes></element><element><type>com.umlet.element.base.Class</type><coordinates><x>200</x><y>10</y><w>100</w><h>30</h></coordinates><panel_attributes>Booking</panel_attributes><additional_attributes>transparentSelection=false</additional_attributes></element><element><type>com.umlet.element.base.Relation</type><coordinates><x>30</x><y>160</y><w>40</w><h>160</h></coordinates><panel_attributes>lt=&lt;-\n" +
      "m1=0..1\n" +
      "r1=mk</panel_attributes><additional_attributes>20;140;20;20</additional_attributes></element></umlet_diagram>";
    //byte[] data = convertMini(source, "png");
    //System.out.println(new String(data));
    //Files.write(Paths.get("result.png"), data);

    byte[] data = convert(source, "png");
    System.out.println(new String(data));
    Files.write(Paths.get("result1.png"), data);
  }

  public static byte[] convert(String source, String format) throws Exception {
    Utils.BuildInfo buildInfo = readBuildInfo();
    Program.init(buildInfo.version, RuntimeType.BATCH);
    ConfigHandler.loadConfig();
    DiagramHandler handler = DiagramHandler.forExport(source);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    OutputHandler.createToStream(format, baos, handler);
    return baos.toByteArray();
  }
}