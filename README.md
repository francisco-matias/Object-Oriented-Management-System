# Stochastic Graph Simulator

A Java-based discrete-event simulation framework for solving complex graph optimization problems using probabilistic metaheuristics and adaptive search strategies.

---

## Overview

This project implements a stochastic simulation engine capable of approximating solutions to NP-hard graph optimization problems.  

The system models weighted graphs and autonomous agents that evolve over time according to probabilistic movement rules, dynamic state updates, and adaptive reinforcement mechanisms.

The simulation is driven by discrete stochastic events and maintains continuous tracking of the best candidate solutions discovered throughout execution.

---

## Core Concepts

- Weighted graph modelling
- Hamiltonian cycle evaluation
- Probabilistic decision-making
- Exponential time distributions
- Discrete-event stochastic simulation
- Adaptive search mechanisms
- Modular object-oriented system design
- Command-line execution and input-driven configuration

---

## Architecture

The system follows a layered modular structure:

### Domain Layer
- Graph representation
- Agent behaviour modelling
- Solution evaluation logic
- State validation and constraint enforcement

### Simulation Engine
- Event scheduling
- Time progression management
- Stochastic event processing
- Dynamic state updates

### Interface Layer
- Command-line parameter parsing
- Random graph generation
- File-based configuration loading
- Structured output formatting

Clear separation of concerns ensures maintainability, extensibility, and controlled system evolution.

---

## Execution

The application runs as an executable JAR file and supports:

- Random graph generation with configurable parameters
- File-based simulation input
- Periodic system observation reporting
- Automatic tracking of best candidate solutions

---

## Technical Highlights

- Advanced object-oriented modelling
- Encapsulation of domain invariants
- Custom exception handling
- Deterministic structure over stochastic processes
- Modular architecture with clean responsibility boundaries

---

## Technologies

- Java
- Object-Oriented Design
- Discrete Event Simulation
- Algorithmic Optimization

