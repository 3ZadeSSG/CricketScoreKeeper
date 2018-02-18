# CricketScoreKeeper
#Purpose
This Score Keeper app is designed to keep track of status which includes runs,wickets,overs of each team in game of cricket.
This project is submited here as a part of Udacity Android Basics Course "Project: Court Counter App".

###How to get a copy
```
You can either download this as a .Zip file or paste the link in Android Studio->Checkout project from version control->GitHub
```
##This is how app looks like
![alt text](https://github.com/manugond/CricketScoreKeeper/blob/master/CricketScreenshot.png)

##How it works
1) First you select the batting team which is either Team A or B
2) Game period here in app is set to 5 overs, so innings of team will end after 5 overs or if team is all out.
3) Scoring buttons marked as 0-6 and extras as WD,NO also wicket is marked as WKT.
4) RESET button will reset the game data.

##Approach Used
1) A Relative Layout is used as containter of other layouts, since we need to adjust Linear Layout of both teams as horizontal and after that buttons and satus card will be at below.
2) Linear Layouts have been used in both vertical and horizontal orentiation, as can be seen from code.
3) This app doesnot stores any data, but storing history will be added later in future as I study more in this course.
