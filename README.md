# darwin_simulator
Welcome!

There's my object-oriented programming project "Darwin Simulator"! The idea is to create a map with jungle, plants and animals then will see what happens. Animals can move, copulate or eat plants which give them energy. But only the strongest will survive! Every animal loses 1 point of energy per day, if it becomes 0, the animal will die. 


So how to turn on the simulation? 
You need to run darwin_simulator[run] in build.gradle or run World.java


Now it's the most important part, time to put in parameters for the simulation. But do it smart if you don't want all animals on your conscience!:

-Size of the map(square, for example, 10 will generate map wth 100 positions): min:10, max:100

-Size of the jungle(also square, in the jungle will generate animals at the start and at least 50% of plants): min:10% of map size, max:50% of map size

-Animal amount(number of animals at the start of simulation): min:10, max:100

-Start energy(Points of energy for animals at the start): min:10, max:100

-Plants amount(number of plants at the start of simulation): min:10, max:100;

-Plant energy(Points of energy for eating the plant): min:10, max:100;

-Minimal energy for copulate(minimal points of energy needed by parents to born new animal): min:5, max:50

Suggested parameters: 50,10,20,30,50,30,10


Ok, simulation turns on, so what is going on?!
On the top of the generated window you can see the map with different squares:

-yellow: plant

-light brown: an animal with +10 points of energy

-dark brown: an animal with 2-9 points of energy 

-black: an animal with 1 point of energy [*] 

-dark green: jungle w/o any object

-green: grass w/o any object


You can click 2 buttons under the map: start(to continue simulation) and stop(to pause simulation).
And on the bottom of the window you've got statistics: 

-Day of simulation

-Number of living animals at the current moment

-Number of living plants at the current moment

-Average points of energy living animals at the current moment

-Average time of life every dead animal

-Number of living children(animals, born after the start of the simulation) in the current moment

Good job! Now you understand how it's working, enjoy!
