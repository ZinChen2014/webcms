/**
 * WebCMS - A content management system (CMS) based on Java
 * Copyright (C) 2015 Simon Wächter (waechter.simon@gmail.com)
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
 * along with this program.  If not, see http://www.gnu.org/licenses/
 */

package ch.swaechter.webcms.textplugin.models;

import ch.swaechter.webcms.core.components.container.Container;
import ch.swaechter.webcms.core.components.model.Model;

/**
 * This class is responsible for all operation on texts.
 *
 * @author Simon Wächter
 */
public class TextModel extends Model
{
	/**
	 * Constructor with the container.
	 *
	 * @param container Container that provides all data
	 */
	public TextModel(Container container)
	{
		super(container);
	}

	/**
	 * Get the model name.
	 *
	 * @return Model name
	 */
	public String getModelName()
	{
		return new String("Textmodel");
	}
}
