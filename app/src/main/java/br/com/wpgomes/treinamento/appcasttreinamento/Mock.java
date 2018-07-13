package br.com.wpgomes.treinamento.appcasttreinamento;

import java.util.ArrayList;
import java.util.List;

import br.com.wpgomes.treinamento.appcasttreinamento.model.Character;
import br.com.wpgomes.treinamento.appcasttreinamento.model.Event;


public class Mock {

    public static List<Character> getCharacters() {

        List<Character> characterList = new ArrayList<>();

        Character character = new Character();
        character.setName("Spider-Man");
        character.setDescription("Bitten by a radioactive spider, high school student Peter " +
                "Parker gained the speed, strength and powers of a spider. Adopting the name Spider-Man, " +
                "Peter hoped to start a career using his new abilities. Taught that with great power comes " +
                "great responsibility, Spidey has vowed to use his powers to help people.");
        character.setThumbnailUrl("http://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b.jpg");


        Character character2 = new Character();
        character2.setName("Iron Man");
        character2.setDescription("Wounded, captured and forced to build a weapon by his enemies, " +
                "billionaire industrialist Tony Stark instead created an advanced suit of armor to save " +
                "his life and escape captivity. Now with a new outlook on life, Tony uses his money and " +
                "intelligence to make the world a safer, better place as Iron Man.");
        character2.setThumbnailUrl("http://i.annihil.us/u/prod/marvel/i/mg/9/c0/527bb7b37ff55.jpg");


        Character character3 = new Character();
        character3.setName("Captain America");
        character3.setDescription("Vowing to serve his country any way he could, young Steve Rogers " +
                "took the super soldier serum to become America's one-man army. Fighting for the red, " +
                "white and blue for over 60 years, Captain America is the living, breathing symbol of " +
                "freedom and liberty.");
        character3.setThumbnailUrl("http://i.annihil.us/u/prod/marvel/i/mg/3/50/537ba56d31087.jpg");

        characterList.add(character);
        characterList.add(character2);
        characterList.add(character3);

        return characterList;


    }


//    public static List<Event> getEvents() {
//
//        List<Event> eventsList = new ArrayList<>();
//
//        Event event = new Event();
//        event.setTitle("Spider-Island");
//        event.setDescription("This summer, heroes, villains and ordinary people across Manhattan gain " +
//                "spider abilities, but not all realize that with great power comes great responsibility! " +
//                "Can Spider-Man keep the peace? Dan Slott spearheads this arachna-tastic event in the pages " +
//                "of Amazing Spider-Man!");
//        event.setUrl("http://marvel.com/comics/events/305/spider-island?utm_campaign=apiRef&utm_source=277232a94db26746c653c879d30cec89");
//        event.setImgUrl("http://i.annihil.us/u/prod/marvel/i/mg/3/70/51e827657c1d0.jpg");
//
//        Event event2 = new Event();
//        event2.setTitle("Age of Apocalypse");
//        event2.setDescription("In a twisted version of the world they knew, the X-Men battle against the eternal mutant " +
//                "pocalypse as Bishop seeks to repair the timeline. Legion, Xavier's own son, attempts to " +
//                "kill off all of Xavier's enemies; however, when Legion attempts to murder Magneto, Xavier " +
//                "sacrifices his own life to save Magnus. As a result, Magneto casts off his anti-human sentiments " +
//                "and carries on Xavier's dream of peaceful co-existence, thereby founding the X-Men.");
//        event2.setUrl("http://marvel.com/comics/events/227/age_of_apocalypse?utm_campaign=apiRef&utm_source=277232a94db26746c653c879d30cec89");
//        event2.setImgUrl("http://i.annihil.us/u/prod/marvel/i/mg/5/e0/51ca0e08a6546.jpg");
//
//        Event event3 = new Event();
//        event3.setTitle("Secret Wars");
//        event3.setDescription("The most powerful heroes and villains in the Marvel Universe square off " +
//                "on Battleword with the victor promised their greatest dreams and desires! Writer Jim Shooter " +
//                "and artists Mike Zeck and Bob Layton introduce the Beyonder in a 12-issue event featuring " +
//                "the Avengers, the X-Men, the Fantastic Four, Spider-Man, Magneto, Doctor Doom and more!");
//        event3.setUrl("http://marvel.com/comics/events/270/secret_wars?utm_campaign=apiRef&utm_source=277232a94db26746c653c879d30cec89");
//        event3.setImgUrl("http://i.annihil.us/u/prod/marvel/i/mg/f/00/51cdeb7048dac.jpg");
//
//        eventsList.add(event);
//        eventsList.add(event2);
//        eventsList.add(event3);
//
//
//        return eventsList;
//
//    }
}