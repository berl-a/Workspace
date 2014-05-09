package com.bg.arkanoid.etypes;

public enum ETypeOfBlock {

	NORMAL_BLOCK(1, 10),
	ARMORED_BLOCK(2, 20),
	BONUS_BLOCK(1, 50);

	
	private int hitsToDestroy;
	private int scoresForDestroying;

	private ETypeOfBlock(int hitsToDest, int scores){
		setHitsToDestroy(hitsToDest);
		setPointsForDestroying(scores);
	}
	
	public int getHitsToDestroy() {
		return hitsToDestroy;
	}

	public void setHitsToDestroy(int hitsToDestroy) {
		this.hitsToDestroy = hitsToDestroy;
	}

	public int getScoresForDestroying() {
		return scoresForDestroying;
	}

	public void setPointsForDestroying(int pointsForDestroying) {
		this.scoresForDestroying = pointsForDestroying;
	}

}
