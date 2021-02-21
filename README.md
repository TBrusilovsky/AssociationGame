# AssociationGame
Association Game for Steelhack 2021  

This game is designed to help players think abstractly about words and develope there vocabulary in a fun, multiplayer method. Two teams of players compete to be the first to connect the dots and figure out the final solution to the association board.   
  
## How to Play
The game starts with a hidden 4x4 board of clues labeled A1 through D4. Each turn, a team chooses to reveal one of these clues. Then that team can choose to guess one of the middle solutons A through D or the final solution. The middle solutions are words associated with the clues of the same letter. The final solution is a word associated with the 4 middle solutions. The fewer clues for a certain solution that have been revealed, the more points that solution is worth to the team that guesses it.

  
## How to Run
There are 2 ways to play the game. You can either play it with a graphic interface or on the command line. Regardless, navigate to the specifed directory in your terminal and then run 2 commands: `javac *.java` and `java AssociationMain`. In both cases, follow the instructions as directed. For best performance, insure that all answers are submitted starting with a capital letter (Dog, not dog and Cow, not cow).


## Adding More Words
Adding more words to the game is relativly simple, but there are a few rules you need to follow. First off, every word should be capitalized and divided from other words by a single space. Multi word phrases should be connected by some non whitespace character. Then, the 'root' word that the words you are adding associate with must be at the start of their own line. If any word in that line already exists as the start of its own line, add a * to the front of that word. For instance, Cow is a word with its own associations, so when I associate Cow with Farm, I write \*Cow instead of just Cow. Beyond that, any word you add needs to have at least 4 such star'd words, otherwise you risk issues cropping up during execution. Lastly, make sure there is no white space at the end of the line. And thats it, pretty simple really!