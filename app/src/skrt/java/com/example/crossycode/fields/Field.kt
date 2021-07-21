package com.example.crossycode.fields
import com.example.crossycode.fields.Seasons;

abstract class Field {
    public var season = Seasons.SPRING;

    constructor(season: Seasons) {
        this.season = season
    }
}