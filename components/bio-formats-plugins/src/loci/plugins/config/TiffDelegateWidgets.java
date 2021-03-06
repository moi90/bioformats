/*
 * #%L
 * Bio-Formats Plugins for ImageJ: a collection of ImageJ plugins including the
 * Bio-Formats Importer, Bio-Formats Exporter, Bio-Formats Macro Extensions,
 * Data Browser and Stack Slicer.
 * %%
 * Copyright (C) 2006 - 2017 Open Microscopy Environment:
 *   - Board of Regents of the University of Wisconsin-Madison
 *   - Glencoe Software, Inc.
 *   - University of Dundee
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

package loci.plugins.config;

import ij.Prefs;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

import loci.plugins.util.LociPrefs;

/**
 * Custom widgets for configuring Bio-Formats TIFF support.
 *
 * @author Curtis Rueden ctrueden at wisc.edu
 */
public class TiffDelegateWidgets implements IFormatWidgets, ItemListener {

  // -- Fields --

  private String[] labels;
  private Component[] widgets;

  // -- Constructor --

  public TiffDelegateWidgets() {
    boolean imageIO = Prefs.get(LociPrefs.PREF_TIFF_IMAGEIO, false);

    String legacyLabel = "JAI";
    JCheckBox legacyBox = new JCheckBox(
      "Use JAI library instead of native TIFF support",
      imageIO);
    legacyBox.addItemListener(this);

    labels = new String[] {legacyLabel};
    widgets = new Component[] {legacyBox};
  }

  // -- IFormatWidgets API methods --

  @Override
  public String[] getLabels() {
    return labels;
  }

  @Override
  public Component[] getWidgets() {
    return widgets;
  }

  // -- ItemListener API methods --

  @Override
  public void itemStateChanged(ItemEvent e) {
    JCheckBox box = (JCheckBox) e.getSource();
    Prefs.set(LociPrefs.PREF_TIFF_IMAGEIO, box.isSelected());
  }

}
