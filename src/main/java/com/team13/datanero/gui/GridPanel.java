package com.team13.datanero.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * A helper class to draw lines to visualize the grid
 */
class GridPanel extends JPanel {
    private int gridSize;

    public GridPanel(int gridSize) {
        this.gridSize = gridSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);

        /* Draw vertical grid lines */
        for (int i = 1; i < gridSize; i++) {
            int x = i * getWidth() / gridSize;
            g2d.drawLine(x, 0, x, getHeight());
        }

        /* Draw horizontal grid lines */
        for (int i = 1; i < gridSize; i++) {
            int y = i * getHeight() / gridSize;
            g2d.drawLine(0, y, getWidth(), y);
        }
    }
}
