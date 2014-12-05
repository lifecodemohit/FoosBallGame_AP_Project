package ProjectTests;

import gameGUI.MyFrame;

import org.testng.Assert;
import org.testng.annotations.Test;

import ball.Ball;
import team.Team;
import team.players.Defender;
import computerPlayer.ComputerPlayer;

public class FieldTest 
{
	MyFrame frame;
	Team teamA;
	Team teamB;
	int level;
	int level2;
	ComputerPlayer computerPlayer1;
	ComputerPlayer computerPlayer2;
	
	FieldTest()
	{
		 
	}
	
	public MyFrame startNewGame()
	{
		frame = new MyFrame("ABC" , "DEF");
		teamA = new Team("TeamA", 2, 4, 4, 1, frame.myPanel);
		teamB = new Team("TeamB", 1, 5, 4, 1, frame.myPanel);
		level = 0;
		level2 = 0;
		computerPlayer1 = new ComputerPlayer(teamA.getPlayersInTeam() , frame.myPanel.getaBall() , frame.myPanel , (50 + (2-level)*100)  );
		computerPlayer2 = new ComputerPlayer(teamB.getPlayersInTeam() , frame.myPanel.getaBall() , frame.myPanel , (50 + (2-level2)*100)  );
		teamA.startTeam();
		teamB.startTeam();
		computerPlayer1.start();
		computerPlayer2.start();
		return frame;
	}
	
	public void endGame(MyFrame f)
	{
		f.dispose();
	}
	
	@Test 
	public void TeamANameSetCorrectly() 
	{
		Team teamA = new Team("TeamA", 2, 4, 4, 1, 0);
		Assert.assertEquals(teamA.getCountryName(), "TeamA");
	}
	
	@Test 
	public void TeamBNameSetCorrectly() 
	{
		Team teamB = new Team("TeamB", 3, 4, 3, 1, 0);
		Assert.assertEquals(teamB.getCountryName(), "TeamB");
	}
	
	@Test
	public void TeamAFormationSetCorrectly()
	{
		Team teamA = new Team("TeamA", 2, 4, 4, 1, 0);
		Assert.assertEquals(teamA.getNumberOfAttackers(), 2);
		Assert.assertEquals(teamA.getNumberOfMidFielder(), 4);
		Assert.assertEquals(teamA.getNumberOfDefender(), 4);
		Assert.assertEquals(teamA.getNumberOfGoalKeeper(), 1);
	}
	
	@Test
	public void TeamBFormationSetCorrectly()
	{
		Team teamB = new Team("TeamA", 3, 4, 3, 1, 0);
		Assert.assertEquals(teamB.getNumberOfAttackers(), 3);
		Assert.assertEquals(teamB.getNumberOfMidFielder(), 4);
		Assert.assertEquals(teamB.getNumberOfDefender(), 3);
		Assert.assertEquals(teamB.getNumberOfGoalKeeper(), 1);
	}
	
	@Test
	public void GoalScoredByTeamA()
	{
		Team teamA = new Team("TeamA", 2, 4, 4, 1, 0);
		int currentScore = teamA.score;
		teamA.score++;
		Assert.assertEquals(teamA.score, currentScore+1);
	}
	
	@Test
	public void GoalScoredByTeamB()
	{
		Team teamB = new Team("TeamA", 3, 4, 3, 1, 1);
		int currentScore = teamB.score;
		teamB.score++;
		Assert.assertEquals(teamB.score, currentScore+1);
	}
	
	@Test
	public void BallSetInProperPosition()
	{
		Ball fBall = new Ball(0,0);
		Assert.assertEquals(fBall.getxAxisPosition(), 0);
		Assert.assertEquals(fBall.getyAxisPosition(), 0);
	}
	
	@Test
	public void TestPassingInTeamA()
	{
		Team teamA = new Team("TeamA", 2, 4, 4, 1, 0);
		Ball fBall = new Ball(0, 0);
		teamA.playersInTeam.add(new Defender("D1", "TeamA", 0, 1));
		teamA.playersInTeam.add(new Defender("D2", "TeamA", 0, 2));
		fBall.pass(teamA.getPlayersInTeam().get(0), teamA.getPlayersInTeam().get(1));
		Assert.assertEquals(fBall.getCurrentContact(), teamA.getPlayersInTeam().get(0));
		Assert.assertEquals(fBall.getFutureContact(), teamA.getPlayersInTeam().get(1));
	}
	
	@Test
	public void TestPassingInTeamB()
	{
		Team teamB = new Team("TeamB", 3, 4, 3, 1, 0);
		Ball fBall = new Ball(0, 0);
		teamB.playersInTeam.add(new Defender("D1", "TeamB", 0, 1));
		teamB.playersInTeam.add(new Defender("D2", "TeamB", 0, 2));
		fBall.pass(teamB.getPlayersInTeam().get(0), teamB.getPlayersInTeam().get(1));
		Assert.assertEquals(fBall.getCurrentContact(), teamB.getPlayersInTeam().get(0));
		Assert.assertEquals(fBall.getFutureContact(), teamB.getPlayersInTeam().get(1));
	}
	
	@Test
	public void CheckPlayerXCoordinateInTeamA()
	{
		Team teamA = new Team("TeamA", 2, 4, 4, 1, 0);
		teamA.playersInTeam.add(new Defender("D1", "TeamA", 0, 1));
		teamA.playersInTeam.add(new Defender("D2", "TeamA", 0, 2));
		Assert.assertEquals(teamA.getPlayersInTeam().get(0).getxAxisPosition(), 0);
		Assert.assertEquals(teamA.getPlayersInTeam().get(1).getxAxisPosition(), 0);
	}
	
	@Test
	public void CheckPlayerYCoordinateInTeamA()
	{
		Team teamA = new Team("TeamA", 2, 4, 4, 1, 0);
		teamA.playersInTeam.add(new Defender("D1", "TeamA", 0, 1));
		teamA.playersInTeam.add(new Defender("D2", "TeamA", 0, 2));
		Assert.assertEquals(teamA.getPlayersInTeam().get(0).getyAxisPosition(), 1);
		Assert.assertEquals(teamA.getPlayersInTeam().get(1).getyAxisPosition(), 2);
	}
	
	@Test
	public void CheckPlayerXCoordinateInTeamB()
	{
		Team teamB = new Team("TeamB", 3, 4, 3, 1, 0);
		teamB.playersInTeam.add(new Defender("D1", "TeamB", 0, 1));
		teamB.playersInTeam.add(new Defender("D2", "TeamB", 0, 2));
		Assert.assertEquals(teamB.getPlayersInTeam().get(0).getxAxisPosition(), 0);
		Assert.assertEquals(teamB.getPlayersInTeam().get(1).getxAxisPosition(), 0);
	}
	
	@Test
	public void CheckPlayerYCoordinateInTeamB()
	{
		Team teamB = new Team("TeamB", 3, 4, 3, 1, 0);
		teamB.playersInTeam.add(new Defender("D1", "TeamB", 0, 1));
		teamB.playersInTeam.add(new Defender("D2", "TeamB", 0, 2));
		Assert.assertEquals(teamB.getPlayersInTeam().get(0).getyAxisPosition(), 1);
		Assert.assertEquals(teamB.getPlayersInTeam().get(1).getyAxisPosition(), 2);
	}
	
	@Test
	public void CheckBallxCoordinateAfterTeamAGoal()
	{
		Team teamA = new Team("TeamA", 2, 4, 4, 1, 0);
		Ball fBall = new Ball(0, 0);
		teamA.score++;
		fBall.resetAfterGoal(teamA);
		Assert.assertEquals(fBall.getxAxisPosition(), 0);
	}
	
	@Test
	public void CheckBallyCoordinateAfterTeamAGoal()
	{
		Team teamA = new Team("TeamA", 2, 4, 4, 1, 0);
		Ball fBall = new Ball(0, 0);
		teamA.score++;
		fBall.resetAfterGoal(teamA);
		Assert.assertEquals(fBall.getyAxisPosition(), 0);
	}
	
	@Test
	public void CheckBallxCoordinateAfterTeamBGoal()
	{
		Team teamB = new Team("TeamB", 3, 4, 3, 1, 0);
		Ball fBall = new Ball(0, 0);
		teamB.score++;
		fBall.resetAfterGoal(teamB);
		Assert.assertEquals(fBall.getxAxisPosition(), 0);
	}
	
	@Test
	public void CheckBallyCoordinateAfterTeamBGoal()
	{
		Team teamB = new Team("TeamB", 3, 4, 3, 1, 0);
		Ball fBall = new Ball(0, 0);
		teamB.score++;
		fBall.resetAfterGoal(teamB);
		Assert.assertEquals(fBall.getyAxisPosition(), 0);
	}
	
	@Test
	public void CheckValidityOfTeamAFormation()
	{
		Team teamA = new Team("TeamA", 2, 4, 4, 1, 0);
		boolean a, m, d, g;
		if(teamA.getNumberOfAttackers()>=1 && teamA.getNumberOfAttackers()<=4)
		{
			a = true;
		}
		else
			a = false;
		if(teamA.getNumberOfMidFielder()>=2 && teamA.getNumberOfMidFielder()<=6)
		{
			m = true;
		}
		else
			m = false;
		if(teamA.getNumberOfDefender()>=3 && teamA.getNumberOfDefender()<=6)
		{
			d = true;
		}
		else
			d = false;
		if(teamA.getNumberOfGoalKeeper()==1)
		{
			g = true;
		}
		else
			g = false;
		Assert.assertEquals(a&&m&&d&&g, true);
	}
	
	@Test
	public void CheckValidityOfTeamBFormation()
	{
		Team teamB = new Team("TeamB", 3, 4, 3, 1, 0);
		boolean a, m, d, g;
		if(teamB.getNumberOfAttackers()>=1 && teamB.getNumberOfAttackers()<=4)
		{
			a = true;
		}
		else
			a = false;
		if(teamB.getNumberOfMidFielder()>=2 && teamB.getNumberOfMidFielder()<=6)
		{
			m = true;
		}
		else
			m = false;
		if(teamB.getNumberOfDefender()>=3 && teamB.getNumberOfDefender()<=6)
		{
			d = true;
		}
		else
			d = false;
		if(teamB.getNumberOfGoalKeeper()==1)
		{
			g = true;
		}
		else
			g = false;
		Assert.assertEquals(a&&m&&d&&g, true);
	}
	
	@Test
	public void CheckValidityOfNumberOfPlayersInTeamA()
	{
		Team teamA = new Team("TeamA", 2, 4, 4, 1, 0);
		Assert.assertEquals(teamA.getNumberOfPlayers(), 11);
	}
	
	@Test
	public void CheckValidityOfNumberOfPlayersInTeamB()
	{
		Team teamB = new Team("TeamB", 3, 4, 3, 1, 0);
		Assert.assertEquals(teamB.getNumberOfPlayers(), 11);
	}
}
