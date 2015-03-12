package com.robmiller.classicgames.blockmatching;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class HighScoreSaveHelper {
}
/*	public static class JsonWorld {
		public float money;
		public int wave;
		public int lives;
		public int points;
		public ArrayList<JsonGun> gunList = new ArrayList<JsonGun>();
	}

	public static class JsonGun {
		public float x;
		public float y;
		public int type;
		public int level;
	}

	public static void saveWorld(World world) {
		JsonWorld jWorld = new JsonWorld();

		for (Gun gun : world.guns) {
			JsonGun jGun = new JsonGun();
			jGun.x = gun.position.x;
			jGun.y = gun.position.y;
			jGun.type = gun.getGunType();
			jGun.level = gun.level;
			jWorld.gunList.add(jGun);
		}

		jWorld.money = world.money;
		jWorld.wave = world.wave;
		jWorld.lives = world.lives;
		jWorld.points = world.points;

		Json json = new Json();
		writeFile("game.sav", json.toJson(jWorld));
	}

	public static World loadWorld() {
		String save = readFile("game.sav");
		if (!save.isEmpty()) {
			World world = new World();

			Json json = new Json();
			JsonWorld jWorld = json.fromJson(JsonWorld.class, save);

			world.money = jWorld.money;
			world.wave = jWorld.wave;
			world.lives = jWorld.lives;
			world.points = jWorld.points;

			if (jWorld.gunList != null) {
				for (JsonGun jGun : jWorld.gunList) {
					Gun gun = new Gun(jGun.type, jGun.x, jGun.y);
					gun.level = jGun.level;
					world.guns.add(gun);
				}
			}

			return world;
		}
		return null;
	}

	public static void writeFile(String fileName, String s) {
		FileHandle file = Gdx.files.local(fileName);
		file.writeString(com.badlogic.gdx.utils.Base64Coder.encodeString(s), false);
	}

	public static String readFile(String fileName) {
		FileHandle file = Gdx.files.local(fileName);
		if (file != null && file.exists()) {
			String s = file.readString();
			if (!s.isEmpty()) {
				return com.badlogic.gdx.utils.Base64Coder.decodeString(s);
			}
		}
		return "";
	}
}*/
