/*
 * Copyright (C) 2017 - Benjamin Dickson, Andrew Odintsov, Zilvinas Ceikauskas,
 * Bijan Ghasemi Afshar
 *
 *
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package Controller;

import static Controller.MainController.isNumeric;

import Model.MultilineString;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by bendickson on 5/6/17.
 */
public class XmlController {

	/**
	 *set import style.
	 */
	public enum ImportAs {
		BOOLEAN, STRING, INTEGER, DOUBLE, MULTILINESTRING, NODELIST
	}

	/**
	 *inner class.
	 */
	public class NodeReturn {
		private ImportAs importedAs;
		private String stringValue;
		private int integerValue;
		private double doubleValue;
		private MultilineString multilineStringValue;
		private NodeList nodeList;
		private boolean booleanValue = false;

		/**get boolean value.
		 * @return boolean
		 */
		public boolean getBoolean() {
			if (importedAs == ImportAs.BOOLEAN) {
				return booleanValue;
			} else {
				return false;
			}
		}

		/**get string value.
		 * @return string
		 */
		public String getString() {
			if (importedAs == ImportAs.STRING) {
				return stringValue;
			} else {
				return null;
			}
		}

		/**get multiline string value.
		 * @return multilineString
		 */
		public MultilineString getMultilineString() {
			if (importedAs == ImportAs.MULTILINESTRING) {
				return multilineStringValue;
			} else {
				return null;
			}
		}

		/**get integer value.
		 * @return int
		 */
		public int getInt() {
			if (importedAs == ImportAs.INTEGER) {
				return integerValue;
			} else {
				return 0;
			}
		}

		/**get double value.
		 * @return double
		 */
		public double getDouble() {
			if (importedAs == ImportAs.DOUBLE) {
				return doubleValue;
			} else {
				return 0;
			}
		}

		/**get node list.
		 * @return NodeList
		 */
		public NodeList getNodeList() {
			if (importedAs == ImportAs.NODELIST) {
				return nodeList;
			} else {
				return null;
			}
		}

		/**set value.
		 * @param nv boolean
		 */
		NodeReturn(boolean nv) {
			importedAs = ImportAs.BOOLEAN;
			booleanValue = nv;
		}

		/**set value.
		 * @param nv int
		 */
		NodeReturn(int nv) {
			importedAs = ImportAs.INTEGER;
			integerValue = nv;
		}

		/**set value.
		 * @param nv double
		 */
		NodeReturn(double nv) {
			importedAs = ImportAs.DOUBLE;
			doubleValue = nv;
		}

		/**set String.
		 * @param nv String
		 */
		NodeReturn(String nv) {
			importedAs = ImportAs.STRING;
			stringValue = nv;
		}

		/**set Multiline String.
		 * @param nv MultilineString
		 */
		NodeReturn(MultilineString nv) {
			importedAs = ImportAs.MULTILINESTRING;
			multilineStringValue = nv;
		}

		/**set Node List.
		 * @param nv NodeList
		 */
		NodeReturn(NodeList nv) {
			importedAs = ImportAs.NODELIST;
			nodeList = nv;
		}
	}

	/**get schema values.
	 * @param nodes NodeList
	 * @param schema HashMap
	 * @return HashMap
	 */
	public HashMap<String, NodeReturn> getSchemaValues(NodeList nodes,
			HashMap<String, ImportAs> schema) {
		HashMap<String, NodeReturn> r = new HashMap<>();
		int i = -1;
		int ii = nodes.getLength();
		String nodeName;
		String temp;
		while (++i < ii) {
			if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				nodeName = nodes.item(i).getNodeName();
				if (schema.containsKey(nodeName) && !r.containsKey(nodeName)) {
					switch (schema.get(nodeName)) {
					case BOOLEAN:
						r.put(nodeName,
								new NodeReturn(nodes.item(i).getTextContent().equals("true")));
						break;
					case STRING:
						r.put(nodeName, new NodeReturn(nodes.item(i).getTextContent()));
						break;
					case MULTILINESTRING:
						r.put(nodeName,new NodeReturn(
								new MultilineString(nodes.item(i).getTextContent())));
						break;
					case INTEGER:
						temp = nodes.item(i).getTextContent();
						if (isNumeric(temp)) {
							r.put(nodeName, new NodeReturn(Integer.parseInt(temp)));
						}
						break;
					case DOUBLE:
						temp = nodes.item(i).getTextContent();
						if (isNumeric(temp)) {
							r.put(nodeName, new NodeReturn(Double.parseDouble(temp)));
						}
						break;
					case NODELIST:
						if (nodes.item(i).hasChildNodes()) {
							r.put(nodeName, new NodeReturn(nodes.item(i).getChildNodes()));
						}
						break;
					default:
						r.put("default", new NodeReturn(0));
						break;
					}
				}
			}
		}
		return r;
	}

	/**get child node
	 * if child node's type is not equal to ELEMENT NODE then remove it.
	 * @param parentNode Node
	 * @return NodeList
	 */
	public static NodeList getNodes(Node parentNode) {
		NodeList tList = parentNode.getChildNodes();
		int i = tList.getLength();
		while (0 < i--) {
			if (tList.item(i).getNodeType() != Node.ELEMENT_NODE) {
				parentNode.removeChild(tList.item(i));
			}
		}
		return parentNode.getChildNodes();
	}

	/** validate node list.
	 * @param nodes NodeList
	 * @param nodeNames String[]
	 * @return boolean
	 */
	@Deprecated
	public static boolean validNodeList(NodeList nodes, String[] nodeNames) {
		int i = -1;
		int ii = nodeNames.length;
		if (nodes.getLength() != ii) {
			return false;
		}
		while (++i < ii) {
			if (!nodes.item(i).getNodeName().equals(nodeNames[i])) {
				return false;
			}
		}
		return true;
	}

	/**compare each element of nodes with Node if
	 * that element has the same ELEMENT_NODE and the same key
	 * then add it to a Set
	 * return the boolean value of the comparation between the size of the Set and the schema Map.
	 * @param nodes NodeList
	 * @param schema HashMap
	 * @return boolean
	 */
	public static boolean matchesSchema(NodeList nodes,
			HashMap<String, XmlController.ImportAs> schema) {
		HashSet<String> match = new HashSet<>();
		int i = -1;
		int ii = nodes.getLength();
		while (++i < ii) {
			if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE
					&& schema.containsKey(nodes.item(i).getNodeName())) {
				match.add(nodes.item(i).getNodeName());
			}
		}
		return match.size() == schema.size();
	}
}
