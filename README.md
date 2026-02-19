# Stochastic Graph Simulator

A Java-based discrete-event simulation framework for solving complex graph optimization problems using probabilistic metaheuristics and adaptive search strategies.

---

## Overview

This project implements a stochastic simulation engine designed to approximate solutions to NP-hard graph optimization problems.

The system models weighted graphs and autonomous agents that evolve over time according to probabilistic movement rules, exponential time distributions, and adaptive reinforcement mechanisms.  

Simulation progresses through discrete stochastic events while continuously tracking the best candidate solutions discovered during execution.

---

## System Design

The application follows a modular layered structure separating domain logic, simulation mechanics, and execution control.

### Domain Model
- Weighted graph representation
- Hamiltonian cycle evaluation
- Agent behaviour modelling
- Constraint enforcement and state validation

### Simulation Core
- Event scheduling
- Time progression management
- Stochastic event processing
- Adaptive state updates

### Execution Layer
- Command-line parameter parsing
- Random graph generation
- File-based configuration loading
- Structured output reporting

Clear separation of responsibilities ensures extensibility, maintainability, and controlled state evolution within a stochastic environment.

---

## Technical Scope

- Java
- Object-Oriented Design
- Discrete Event Simulation
- Probabilistic Modelling
- Graph Optimization
- Modular System Architecture

---

## Architectural Design (UML)

The system architecture reflects a structured object-oriented design with explicit ownership of state transitions and clear interaction boundaries between components.

The UML diagram illustrates:

- Core domain entities and their relationships  
- Encapsulation of graph and agent behaviour  
- Interaction between event scheduling and state evolution  
- Separation between simulation engine and optimization logic  

The design emphasizes modularity, controlled mutation of shared state, and extensibility for alternative search strategies or simulation policies.

![UML Diagram]()

