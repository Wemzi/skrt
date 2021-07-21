package com.example.crossycode.fields
open class Color(i: Int, i1: Int, i2: Int, i3: Int);

class Grass(season: Seasons) : Field(season) {
    public var grassColor = Color(255,0,0,0);
}