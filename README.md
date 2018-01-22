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

## License

[MIT License][Arbitrary case-insensitive reference text]

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

###### Copyright (c) 2018 Abdullah Yahya

[arbitrary case-insensitive reference text]: https://ayahya.mit-license.org