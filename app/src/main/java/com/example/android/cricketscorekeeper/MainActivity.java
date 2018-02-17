package com.example.android.cricketscorekeeper;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    int scoreOfTeamA = 0; //score of team A
    int scoreOfTeamB = 0; //score of team B
    int target = 0;       //target to win game
    int teamAOvers = 0;   //overs played by team A
    int teamBOvers = 0;   //overs played by team B
    int teamABalls = 0;   //balls played by team A
    int teamBBalls = 0;   //balls played by team B
    int teamAExtras = 0;  //extra runs for team A
    int teamBExtras = 0;  //extra runs for team B
    int teamAWickets = 0; //wickets of team A
    int teamBWickets = 0; //wickets of team B
    char currently_playing = 'A';
    String status;
    boolean wasnoball = false;
    char tossWon;
    boolean gameEnd = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private void increment_runs(int num) {
        if (!gameEnd) {
            if (currently_playing == 'A') {
                if (++teamABalls == 6) {
                    teamABalls = 0;
                    teamAOvers++;
                }
                scoreOfTeamA += num;
                if (tossWon == 'B' && target <= scoreOfTeamA) {
                    status = "Team A won by " + (10 - teamAWickets) + " wickets";
                    gameEnd = true;
                } else if (teamAOvers == 5) {
                    if (tossWon == 'A') {
                        currently_playing = 'B';
                        target = scoreOfTeamA + 1;
                    } else {
                        if (scoreOfTeamA < target) {
                            status = "Team A lost by  " + (target - scoreOfTeamA) + " runs";
                            gameEnd = true;
                        }
                    }
                }
            } else if (currently_playing == 'B') {
                if (++teamBBalls == 6) {
                    teamBBalls = 0;
                    teamBOvers++;
                }
                scoreOfTeamB += num;
                if (tossWon == 'A' && target <= scoreOfTeamB) {
                    status = "Team B won by " + (10 - teamBWickets) + " wickets";
                    gameEnd = true;
                } else if (teamBOvers == 5) {
                    if (tossWon == 'B') {
                        currently_playing = 'A';
                        target = scoreOfTeamB + 1;
                    } else {
                        if (scoreOfTeamB < target) {
                            status = "Team B lost by  " + (target - scoreOfTeamB) + " runs";
                            gameEnd = true;
                        }
                    }
                }
            }
            wasnoball = false;
        }
    }
    public void zeroRuns(View view) {
        if (!gameEnd) {
            increment_runs(0);
        }
        displaystatus();
    }
    //If one runs is scored
    public void oneRuns(View view) {
        if (!gameEnd) {
            increment_runs(1);
        }
        displaystatus();
    }

    //If two runs is scored
    public void twoRuns(View view) {
        if (!gameEnd) {
            increment_runs(2);
        }
        displaystatus();
    }
    public void threeRuns(View view) {
        if (!gameEnd) {
            increment_runs(3);
        }
        displaystatus();
    }

    public void fourRuns(View view) {
        if (!gameEnd) {
            increment_runs(4);
        }
        displaystatus();
    }

    public void fiveRuns(View view) {
        if (!gameEnd) {
            increment_runs(5);
        }
        displaystatus();
    }

    public void sixRuns(View view) {
        if (!gameEnd) {
            increment_runs(6);
        }
        displaystatus();
    }

    //In case of wide ball no balls will be incremented only score will incremented
    public void wide(View view) {
        if (!gameEnd) {
            if (currently_playing == 'A') {
                scoreOfTeamA++;
                teamAExtras++;
            } else if (currently_playing == 'B') {
                scoreOfTeamB++;
                teamBExtras++;
            }
        }
        displaystatus();
    }

    public void noball(View view) {
        if (!gameEnd) {
            wasnoball = true;
            if (currently_playing == 'A') {
                scoreOfTeamA++;
                teamAExtras++;
                if (tossWon == 'B' && target <= scoreOfTeamA) {
                    status = "Team A won by " + (10 - teamAWickets) + " wickets";
                    gameEnd = true;
                }
            } else if (currently_playing == 'B') {
                scoreOfTeamB++;
                teamBExtras++;
                if (tossWon == 'A' && target <= scoreOfTeamA) {
                    status = "Team B won by " + (10 - teamBWickets) + " wickets";
                    gameEnd = true;
                }
            }
        }
        displaystatus();
    }

    public void wicket(View view) {
        if (!wasnoball && !gameEnd) {
            if (currently_playing == 'A') {
                if (++teamAWickets == 10) {
                    if (tossWon == 'A') {
                        status = "Team A all-out! Target is: " + (scoreOfTeamA + 1);
                        target=scoreOfTeamA+1;
                        currently_playing = 'B';
                    } else {
                        status = "Team A lost game by " + (target - scoreOfTeamA) + " runs!";
                        gameEnd = true;
                    }
                }
            } else if (currently_playing == 'B') {
                if (++teamBWickets == 10) {
                    if (tossWon == 'B') {
                        status = "Team B all-out! Target is: " + (scoreOfTeamB + 1);
                        currently_playing = 'A';
                        target=scoreOfTeamB+1;
                    } else {
                        status = "Team B lost game by " + (target - scoreOfTeamB) + " runs";
                        gameEnd = true;
                    }
                }
            }
        } else {
            wasnoball = false;
        }
        displaystatus();
    }

    private void displaystatus() {
        TextView a = findViewById(R.id.team_a_score);
        a.setText(scoreOfTeamA + "/" + teamAWickets);
        a = findViewById(R.id.team_b_score);
        a.setText(scoreOfTeamB + "/" + teamBWickets);
        a = findViewById(R.id.view_status);
        a.setText("Status: "+status);
        a = findViewById(R.id.a_extra);
        a.setText("Extras: "+teamAExtras);
        a = findViewById(R.id.a_overs);
        a.setText("Overs: "+teamAOvers+"."+teamABalls);
        a = findViewById(R.id.b_extra);
        a.setText("Extras: "+teamBExtras);
        a = findViewById(R.id.b_overs);
        a.setText("Overs: "+teamBOvers+"."+teamBBalls);
    }

    public void team_a_toss(View view) {
        if(gameEnd) {
            tossWon = 'A';
            resetall();
            currently_playing = 'A';
            gameEnd = false;
            status = "Team A batting";
            displaystatus();
        }
    }
    public void team_b_toss(View view) {
        if(gameEnd) {
            tossWon = 'B';
            resetall();
            currently_playing = 'B';
            gameEnd = false;
            status = "Team B batting";
            displaystatus();
        }
    }

    public void reset(View view) {
        resetall();
        displaystatus();
    }

    private void resetall() {
        gameEnd = true;
        scoreOfTeamA = 0;
        scoreOfTeamB = 0;
        target = 0;
        teamAOvers = 0;
        teamBOvers = 0;
        teamABalls = 0;
        teamBBalls = 0;
        teamAExtras = 0;
        teamBExtras = 0;
        teamAWickets = 0;
        teamBWickets = 0;
        status = "RESET Done!";
    }

}
