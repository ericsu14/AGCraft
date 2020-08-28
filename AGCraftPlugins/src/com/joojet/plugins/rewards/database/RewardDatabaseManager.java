package com.joojet.plugins.rewards.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import com.joojet.plugins.rewards.enums.MinigameRewardType;
import com.joojet.plugins.rewards.enums.RewardType;
import com.joojet.plugins.rewards.interfaces.RewardEntry;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RewardDatabaseManager 
{
	/** Grants a new reward to a player
	 * 		@param uuid - The UUID of the player the reward is being granted to
	 * 		@param reward - The type of reward that is being granted to the player
	 * 		@param event - The name of the event from where the reward is distributed 
	 * 		@throws SQLException - Internal connection error */
	public static void grantReward (UUID uuid, RewardType reward, MinigameRewardType event) throws SQLException
	{
		Connection c = null;
		
		c = DriverManager.getConnection(CreateRewardsDatabase.kDatabasePath);
		c.setAutoCommit(false);
		
		StringBuilder query = new StringBuilder ("INSERT INTO ");
		query.append(CreateRewardsDatabase.kRewardsDatabaseName);
		query.append(" (UUID, REWARD, EVENT, CLAIMED) VALUES (?,?,?,?)");
		
		PreparedStatement pstmt = c.prepareStatement(query.toString());
		pstmt.setString(1, uuid.toString());
		pstmt.setString(2, reward.toString());
		pstmt.setString(3, event.toString());
		pstmt.setBoolean(4, false);
		
		pstmt.executeUpdate();
		pstmt.close();
		c.commit();
		c.close();
	}
	
	/** Claims a reward given a passed reward ID
	 * 		@param rewardID - The unique ID of the reward being claimed 
	 * 		@throws SQLException - Internal connection error */
	public static void claimReward (int rewardID) throws SQLException
	{
		Connection c = null;
		
		c = DriverManager.getConnection(CreateRewardsDatabase.kDatabasePath);
		c.setAutoCommit(false);
		
		StringBuilder query = new StringBuilder ("UPDATE ");
		query.append(CreateRewardsDatabase.kRewardsDatabaseName);
		query.append(" SET CLAIMED = ?");
		query.append(" WHERE REWARD_ID = ?");
		
		PreparedStatement pstmt = c.prepareStatement(query.toString());
		pstmt.setBoolean(1, true);
		pstmt.setInt(2, rewardID);
		
		pstmt.executeUpdate();
		pstmt.close();
		c.commit();
		c.close();
	}
	
	/** Fetches all unclaimed or claimed rewards granted to a specific player depending on the @param claimed.
	 * 		@param uuid - UUID of the player we are fetching the rewards for.
	 * 		@param claimed - Fetched all unclaimed or claimed rewards
	 * 		@throws SQLException - Internal connection error */
	public static ArrayList <RewardEntry> fetchRewards (UUID uuid, boolean claimed) throws SQLException
	{
		Connection c = null;
		ResultSet result = null;
		RewardEntry entry = null;
		
		ArrayList <RewardEntry> entries = new ArrayList <RewardEntry> ();
		
		c = DriverManager.getConnection(CreateRewardsDatabase.kDatabasePath);
		
		StringBuilder query = new StringBuilder ();
		query.append("SELECT * FROM ");
		query.append(CreateRewardsDatabase.kRewardsDatabaseName);
		query.append(" WHERE UUID = ? AND CLAIMED = ?");
		
		PreparedStatement pstmt = c.prepareStatement(query.toString());
		pstmt.setString(1, uuid.toString());
		pstmt.setBoolean(2, claimed);
		
		result = pstmt.executeQuery();
		
		if (result.next())
		{
			do {
				entry = new RewardEntry (
					result.getInt("REWARD_ID"), 
					result.getString("UUID"), 
					result.getString ("REWARD"), 
					result.getString("EVENT"), 
					result.getBoolean("CLAIMED")
				);
				entries.add(entry);
			} while (result.next());
		}
		
		return entries;
	}
	
	/** Fetches all rewards granted to a specific player
	 * 		@param uuid - UUID of the player we are fetching rewards for
	 * 		@throws SQLException - Internal connection error */
	public static ArrayList <RewardEntry> fetchAllRewards (UUID uuid) throws SQLException
	{
		Connection c = null;
		ResultSet result = null;
		RewardEntry entry = null;
		
		ArrayList <RewardEntry> entries = new ArrayList <RewardEntry> ();
		
		c = DriverManager.getConnection(CreateRewardsDatabase.kDatabasePath);
		
		StringBuilder query = new StringBuilder ();
		query.append("SELECT * FROM ");
		query.append(CreateRewardsDatabase.kRewardsDatabaseName);
		query.append(" WHERE UUID = ?");
		
		PreparedStatement pstmt = c.prepareStatement(query.toString());
		pstmt.setString(1, uuid.toString());
		
		result = pstmt.executeQuery();
		
		if (result.next())
		{
			do {
				entry = new RewardEntry (
					result.getInt("REWARD_ID"), 
					result.getString("UUID"), 
					result.getString ("REWARD"), 
					result.getString("EVENT"), 
					result.getBoolean("CLAIMED")
				);
				entries.add(entry);
			} while (result.next());
		}
		
		return entries;
	}
	
	/** Fetches all unclaimed rewards from a player
	 * 		@param uuid - Player we are fetching all unclaimed rewards for
	 * 		@throws SQLException - Internal connection error */
	public static ArrayList <RewardEntry> fetchUnclaimedRewards (UUID uuid) throws SQLException
	{
		return fetchRewards (uuid, false);
	}
	
	/** Checks if a player already has a reward from the current event
	 * 		@param UUID - uuid of the player we are checking
	 * 		@param event - Type of event this is 
	 * 		@throws SQLException - Internal connection error */
	public static boolean checkIfPlayerHasReward (UUID uuid, MinigameRewardType event) throws SQLException
	{
		ArrayList <RewardEntry> rewards = fetchAllRewards (uuid);
		
		for (RewardEntry entry : rewards)
		{
			if (event == entry.getEvent())
			{
				return true;
			}
		}
		return false;
	}
}
