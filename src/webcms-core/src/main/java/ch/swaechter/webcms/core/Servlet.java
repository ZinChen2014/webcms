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

package ch.swaechter.webcms.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is responsible for the servlet management and uses the platform for the task.
 * A CMS application has to subclass this class to set all required information.
 *
 * @author Simon Wächter
 */
public class Servlet extends HttpServlet
{
	/**
	 * Generated serialization ID.
	 */
	private final static long serialVersionUID = 1L;

	/**
	 * Constructor with the required settings.
	 */
	public Servlet()
	{
	}

	/**
	 * Handle a GET request and send the generated content as response.
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter writer = response.getWriter();
		writer.println("You see the WebCMS servlet.");
		writer.close();
	}

	/**
	 * Handle a POST request and send the generated content as response.
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter writer = response.getWriter();
		writer.println("You see the WebCMS servlet.");
		writer.close();
	}
}
