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

package ch.swaechter.webcms.adminplugin.controllers;

import ch.swaechter.webcms.adminplugin.models.UserModel;
import ch.swaechter.webcms.core.components.container.Container;
import ch.swaechter.webcms.core.components.controller.Controller;
import ch.swaechter.webcms.core.components.view.EmptyView;
import ch.swaechter.webcms.core.components.view.ExternalRedirectView;
import ch.swaechter.webcms.core.components.view.InternalRedirectView;
import ch.swaechter.webcms.core.components.view.ModelView;
import ch.swaechter.webcms.core.components.view.View;

/**
 * This class is responsible for the user actions like the login or logout process.
 *
 * @author Simon Wächter
 */
public class UserController extends Controller
{
	/**
	 * Model that contains the user information.
	 */
	private final UserModel usermodel = new UserModel(getContainer());

	/**
	 * Constructor with the container.
	 *
	 * @param container Container that provides all data
	 */
	public UserController(Container container)
	{
		super(container);
	}

	/**
	 * Index action of the user controller.
	 *
	 * @return Normal JSP view
	 */
	public View index()
	{
		ModelView view = new ModelView("userindex");
		view.addAttribute("modelname", usermodel.getModelName());
		return view;
	}

	/**
	 * Redirect to an internal site.
	 *
	 * @return Redirect view
	 */
	public View internal()
	{
		return new InternalRedirectView("/user/index");
	}

	/**
	 * Redirect to an external site.
	 *
	 * @return Redirect view
	 */
	public View external()
	{
		return new ExternalRedirectView("http://google.com");
	}

	/**
	 * Redirect to an empty view.
	 *
	 * @return Empty view
	 */
	public View donothing()
	{
		return new EmptyView();
	}
}
