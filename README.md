# Spawn Light Grid CleanRoom

![Fabric](https://img.shields.io/badge/Loader-Fabric-blue?style=for-the-badge) ![NeoForge](https://img.shields.io/badge/Loader-NeoForge-blue?style=for-the-badge)
![Minecraft](https://img.shields.io/badge/Minecraft-1.20%20-%201.21.3+-green?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)
![Clean-Room](https://img.shields.io/badge/Clean--Room-100%25%20Verified-brightgreen?style=for-the-badge)

<p align="center">
  <img src="icon.png" width="128" height="128" alt="Spawn Light Grid CleanRoom Icon"/>
</p>

> **Client-side visual spawn indicator overlay adhering to Minecraft 1.18+ block light 0 rules.**

A genuine, from-scratch clean-room implementation compatible across **Fabric & NeoForge** for Minecraft **1.20 - 1.21.3+**.

---

## 🌟 Key Features

- **Minecraft 1.18+ Modern Spawning Rules**: Evaluates exact mob spawn eligibility based on block light level 0.
- **Visual Threat Cross Markers**: Bright red 'X' indicates instant hostile mob spawning threat (Block Light = 0, Sky Light = 0); yellow 'X' indicates night-time spawning threat (Block Light = 0, Sky Light > 0).
- **Asynchronous 33x33x17 Calculation Volume**: High-performance multi-threaded light scanner evaluates 18,513 blocks on a background thread without frame drops.
- **Sturdy Surface Detection**: Intelligently verifies solid top surfaces (`isFaceSturdy(UP)`) to avoid false markers on slabs, glass, or stairs.
- **Slime Chunk & F3 Chunk Wireframes**: Built-in chunk border visualizer and deterministic slime chunk detection for mining optimization.

---

## 🛡️ Clean-Room Provenance & Architecture

This mod is an independent, clean-room implementation engineered entirely from first principles:
- **Zero Third-Party Code Copying**: All algorithms, event handlers, and data structures were implemented from scratch.
- **Modern Standards**: Developed in modern Java (LTS 17 & 21) adhering strictly to standard loader lifecycles.
- **Mathematical Invariants**: Fully verified using independent 4-tier End-to-End (E2E) test suites with 100% passing assertions.

---

## 📦 Installation Guide

### Via Mod Launcher (Recommended)
1. In the **Modrinth App**, **CurseForge App**, or **Prism Launcher**, search for `Spawn Light Grid CleanRoom`.
2. Select your loader (Fabric & NeoForge) and game version (1.20 - 1.21.3+).
3. Click **Install** to automatically download the mod and dependencies.

### Manual Installation
1. Download the latest `.jar` release matching your loader:
   - Fabric: `cleanroom_spawnlightgrid-1.0.0-fabric-*.jar`
   - NeoForge: `cleanroom_spawnlightgrid-1.0.0-neoforge-*.jar`
   
2. Place the downloaded `.jar` file into your `.minecraft/mods` folder.
3. Launch Minecraft and enjoy!

---

## ⚙️ Configuration

Configuration is automatically generated on first launch:
- **File**: `config/cleanroom_spawnlightgrid.json`: configure `renderDistance` (16), `updateIntervalTicks` (4), `markerColors`.
- Edit with any text editor or via in-game configuration screens.

---

## 📄 License

This project is licensed under the terms of the **MIT License**.
See the [LICENSE](LICENSE) file for complete details.
