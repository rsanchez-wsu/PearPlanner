/*
 * Copyright (C) 2018 - Mark Riedel
 * 
 *   
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

package edu.wright.cs.raiderplanner.model;

import edu.wright.cs.raiderplanner.controller.MainController;
import edu.wright.cs.raiderplanner.controller.MenuController;
import edu.wright.cs.raiderplanner.view.UiManager;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Model for the Graduation planner accessed in the
 * GradPlannerController
 *
 * @author Mark Riedel on 10/16/2018.
 */
public class GradPlanner  {
	
	public void open(MenuController.Window current) {
		try {
			MainController.ui.showGradPlanner();
		} catch (IOException e) {
			UiManager.reportError("Unable to open view file");
		}
	}
}