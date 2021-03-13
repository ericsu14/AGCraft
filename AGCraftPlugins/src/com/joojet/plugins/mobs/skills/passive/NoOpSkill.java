package com.joojet.plugins.mobs.skills.passive;

/** A passive skill that has no propetries, but exists as a technical skill to keep the
 *  monster active in our mob-skill runnable instance. This is used to keep track
 *  of the amount of active custom monsters for its type in the game, even if that
 *  custom monster does not have any custom skills to use. */
public class NoOpSkill extends AbstractPassiveSkill 
{

}
