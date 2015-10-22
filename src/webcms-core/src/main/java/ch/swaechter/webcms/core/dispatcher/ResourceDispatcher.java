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

package ch.swaechter.webcms.core.dispatcher;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.omg.CORBA.SystemException;

import ch.swaechter.webcms.core.Globals;
import ch.swaechter.webcms.core.Util;
import ch.swaechter.webcms.core.components.container.Container;
import ch.swaechter.webcms.core.plugin.Plugin;
import ch.swaechter.webcms.core.router.Route;

/**
 * This class represents the resource dispatcher that handles system resources.
 *
 * @author Simon Wächter
 */
public class ResourceDispatcher extends Dispatcher
{
	/**
	 * This method handles the given route. In case of a match the method should return true and the router
	 * will stop looking for the next dispatcher - otherwise return false and the router continues.
	 */
	@Override
	public boolean dispatchRoute(Route route, Container container) throws Exception
	{
		return dispatchResourceRoute(route, container);
	}

	/**
	 * Handle a resource route.
	 *
	 * @param route Route
	 * @param container Container with the data
	 * @return Status of the dispatch process
	 * @throws SystemException An exception in case of a critical system failure
	 */
	private boolean dispatchResourceRoute(Route route, Container container) throws Exception
	{
		String uripath = route.getRequest().getRequestURI().substring(route.getRequest().getContextPath().length());
		String filepath = Util.trimFirstCharacters(uripath, Globals.DIRECTORY_SEPARATOR + container.getSettings().getResourcePrefix());
		if(!filepath.equals(Globals.DIRECTORY_SEPARATOR))
		{
			InputStream servletinputstream = route.getContext().getResourceAsStream(Globals.WEBAPP_WEBINF_DIRECTORY + filepath);
			if(servletinputstream != null)
			{
				try
				{
					copyStream(servletinputstream, route.getResponse().getOutputStream());
					return true;
				}
				catch(IOException exception)
				{
					return false;
				}
			}
			else
			{
				for(Plugin plugin : container.getPluginManager().getPlugins())
				{
					InputStream plugininputstream = plugin.getClass().getResourceAsStream(filepath);
					if(plugininputstream != null)
					{
						try
						{
							copyStream(plugininputstream, route.getResponse().getOutputStream());
							return true;
						}
						catch(IOException exception)
						{
							return false;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Copy the input stream to the output stream.
	 *
	 * @param inputstream Input stream
	 * @param outputstream Output stream
	 * @throws IOException An exception in case of an IO problem
	 */
	private void copyStream(InputStream inputstream, OutputStream outputstream) throws IOException
	{
		int counter = 0;
		byte buffer[] = new byte[8192];
		while((counter = inputstream.read(buffer, 0, buffer.length)) > 0)
		{
			outputstream.write(buffer, 0, counter);
			outputstream.flush();
		}
		outputstream.close();
		inputstream.close();
	}
}
