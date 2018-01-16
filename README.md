# Hack-a-thon

This web app gives coordinators the tools they need to manage their hack-a-thon events. At a glance, coordinators are able to quickly view all participating teams as well as each team's members. They can create/update teams as well as each individual member.

## Behavior Specifications
| Behavior | Input | Expected Behavior |
| :------------- | :------------- | :------------- |
| Create a team | Team name 'test' | Create team with team name but no description |
| Create a team | Team name 'test' description 'test' | Create team with team name/description |
| Add member | Member (first) 'Abdul', (last) 'Yahya', (age) 23, (description) empty | Create member 'ay:id' w/o description and assign to team |
| Add member | Member (first) 'Abdul', (last) 'Yahya', (age) 23, (description) 'Loves naps' | Create member 'ay:id' w/description and assign to team |
| Edit team | Change team name | Update team name |


## Setup/Installation Requirements

* Install the latest Java JDK and JRE
* Install an IDE - preferably IntelliJ IDEA
* clone this repo. <br />
`$ git clone https://github.com/AbdulYahya/hack-a-thon.git`
* Open the project hack-a-thon in your IDE <br />
* Build and Run the project using your IDE

## To Do

* Complete individual member UI
* Add functionality allowing coordinators to edit/update/delete members
* Complete all 'coming soon' features

### License

MIT License

Copyright (c) 2018 Abdullah Yahya
