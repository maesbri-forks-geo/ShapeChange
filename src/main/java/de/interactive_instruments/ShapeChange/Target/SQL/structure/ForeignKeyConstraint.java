/**
 * ShapeChange - processing application schemas for geographic information
 *
 * This file is part of ShapeChange. ShapeChange takes a ISO 19109
 * Application Schema from a UML model and translates it into a
 * GML Application Schema or other implementation representations.
 *
 * Additional information about the software can be found at
 * http://shapechange.net/
 *
 * (c) 2002-2017 interactive instruments GmbH, Bonn, Germany
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
 * Contact:
 * interactive instruments GmbH
 * Trierer Strasse 70-72
 * 53115 Bonn
 * Germany
 */
package de.interactive_instruments.ShapeChange.Target.SQL.structure;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import de.interactive_instruments.ShapeChange.Target.SQL.SqlUtil;

/**
 * @author Johannes Echterhoff (echterhoff at interactive-instruments
 *         dot de)
 *
 */
public class ForeignKeyConstraint extends SqlConstraint {

	public enum Option {

		NO_ACTION("NO ACTION"), CASCADE("CASCADE"), RESTRICT(
				"RESTRICT"), SET_NULL("SET NULL"), SET_DEFAULT("SET DEFAULT");

		private final String sqlExpr;

		private Option(String sqlExpr) {
			this.sqlExpr = sqlExpr;
		}

		public String toString() {
			return sqlExpr;
		}

		public static Option fromString(String s) {

			if (StringUtils.isNotBlank(s)) {
				if (s.matches("(?i)\\s*no\\s*action\\s*")) {
					return ForeignKeyConstraint.Option.NO_ACTION;
				} else if (s.matches("(?i)\\s*restrict\\s*")) {
					return ForeignKeyConstraint.Option.RESTRICT;
				} else if (s.matches("(?i)\\s*set\\s*null\\s*")) {
					return ForeignKeyConstraint.Option.SET_NULL;
				} else if (s.matches("(?i)\\s*set\\s*default\\s*")) {
					return ForeignKeyConstraint.Option.SET_DEFAULT;
				} else if (s.matches("(?i)\\s*cascade\\s*")) {
					return ForeignKeyConstraint.Option.CASCADE;
				} else {
					throw new IllegalArgumentException(
							"Cannot determine enum from: " + s);
				}
			} else {
				throw new IllegalArgumentException(
						"Cannot determine enum from blank string (null, empty, or whitespace only).");
			}

		}
	}

	private Table referenceTable = null;
	private List<Column> referenceColumns = new ArrayList<Column>();

	private Option onDelete = null;
	private Option onUpdate = null;

	public ForeignKeyConstraint(String name, Table referenceTable) {
		super(name);
		this.referenceTable = referenceTable;
	}

	/**
	 * @return the referenceTable
	 */
	public Table getReferenceTable() {
		return referenceTable;
	}

	/**
	 * @param referenceTable
	 *            the referenceTable to set
	 */
	public void setReferenceTable(Table referenceTable) {
		this.referenceTable = referenceTable;
	}

	/**
	 * @return the referenceColumns
	 */
	public List<Column> getReferenceColumns() {
		return referenceColumns;
	}

	/**
	 * @param referenceColumns
	 *            the referenceColumns to set
	 */
	public void setReferenceColumns(List<Column> referenceColumns) {
		this.referenceColumns = referenceColumns;
	}

	public void addReferenceColumn(Column refColumn) {
		this.referenceColumns.add(refColumn);
	}

	/**
	 * @return the option for ON DELETE; can be <code>null</code>
	 */
	public Option getOnDelete() {
		return onDelete;
	}

	public boolean hasOnDelete() {
		return onDelete != null;
	}

	public void setOnDelete(Option o) {
		this.onDelete = o;
	}

	public boolean hasOnUpdate() {
		return onUpdate != null;
	}

	/**
	 * @return the option for ON UPDATE; can be <code>null</code>
	 */
	public Option getOnUpdate() {
		return onUpdate;
	}

	public void setOnUpdate(Option o) {
		this.onUpdate = o;
	}

	@Override
	public String toString() {

		return (this.hasName() ? "CONSTRAINT " + getName() + " " : "")
				+ "FOREIGN KEY "
				+ SqlUtil.getStringList(this.getColumns(), true, true)
				+ " REFERENCES " + this.referenceTable.getName()
				+ ((this.referenceColumns != null
						&& !this.referenceColumns.isEmpty())
								? SqlUtil.getStringList(referenceColumns, true,
										true)
								: "")
				+ (onDelete != null ? " ON DELETE " + onDelete.toString() : "")
				+ (onUpdate != null ? " ON UPDATE " + onUpdate.toString() : "");
	}
}
