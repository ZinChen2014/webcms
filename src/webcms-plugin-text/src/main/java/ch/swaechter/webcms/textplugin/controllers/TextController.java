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

package ch.swaechter.webcms.textplugin.controllers;

import ch.swaechter.webcms.core.components.container.Container;
import ch.swaechter.webcms.core.components.controller.Controller;
import ch.swaechter.webcms.core.components.view.ModelView;
import ch.swaechter.webcms.core.components.view.View;
import ch.swaechter.webcms.textplugin.models.TextModel;

/**
 * This class is responsible for the text management.
 *
 * @author Simon Wächter
 */
public class TextController extends Controller
{
	/**
	 * Model that contains the text information.
	 */
	private final TextModel textmodel = new TextModel(getContainer());

	/**
	 * Constructor with the container.
	 *
	 * @param container Container that provides all data
	 */
	public TextController(Container container)
	{
		super(container);
	}

	/**
	 * Index action of the text controller.
	 *
	 * @return Normal JSP view
	 */
	public View index()
	{
		ModelView view = new ModelView("textindex");
		view.addAttribute("modelname", textmodel.getModelName());
		return view;
	}
}