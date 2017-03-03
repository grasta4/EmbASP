package it.unical.mat.embasp.specializations.solver_planning_domains;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.unical.mat.embasp.languages.pddl.Action;
import it.unical.mat.embasp.languages.pddl.Plan;

public class SolverPlanningDomainPlan extends Plan {

	public SolverPlanningDomainPlan(final String plan, final String error) {
		super(plan, error);
	}

	@Override
	protected void parse() {
		if (errors != "" || output == "")
			return;
		final JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			obj = (JSONObject) parser.parse(output);
			final String status = (String) obj.get("status");
			if (status.contains("ok")) {
				final JSONArray arrayPlan = (JSONArray) ((JSONObject) obj.get("result")).get("plan");
				for (int i = 0; i < arrayPlan.size(); i++)
					actionSequence.add(new Action(((JSONObject) arrayPlan.get(i)).get("name").toString()));
			} else
				errors += " " + (String) ((JSONObject) obj.get("result")).get("error");
		} catch (final ParseException e) {
			errors += " ParseException : " + e.getMessage();
		}
	}

}