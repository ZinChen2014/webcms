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

package ch.swaechter.webcms.core.router;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class represents the request with the path and all parameters.
 *
 * @author Simon Wächter
 */
public class Route
{
	/**
	 * Path for the CMS system.
	 */
	private final String path;

	/**
	 * Servlet context.
	 */
	private final ServletContext context;

	/**
	 * HTTP request.
	 */
	private final HttpServletRequest request;

	/**
	 * HTTP response.
	 */
	private final HttpServletResponse response;

	/**
	 * Constructor with the CMS path and all request parameters.
	 *
	 * @param path CMS path
	 * @param context Servlet context
	 * @param request HTTP request
	 * @param response HTTP response
	 */
	public Route(String path, ServletContext context, HttpServletRequest request, HttpServletResponse response)
	{
		this.path = path;
		this.context = context;
		this.request = request;
		this.response = response;
	}

	/**
	 * Get the CMS path.
	 *
	 * @return CMS path
	 */
	public String getPath()
	{
		return path;
	}

	/**
	 * Get the servlet context.
	 *
	 * @return Servlet context
	 */
	public ServletContext getContext()
	{
		return context;
	}

	/**
	 * Get the HTTP request.
	 *
	 * @return HTTP request
	 */
	public HttpServletRequest getRequest()
	{
		return request;
	}

	/**
	 * Get the HTTP response.
	 *
	 * @return HTTP resonse
	 */
	public HttpServletResponse getResponse()
	{
		return response;
	}
}
