// Date: 03/04/2014 23:47:34
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package heero.wakcraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelGobball extends ModelGenericGobball {
	ModelRenderer hornL;
	ModelRenderer hornR;

	public ModelGobball() {
		hornL = new ModelRenderer(this, 0, 0);
		hornL.addBox(0F, 0F, 0F, 4, 2, 2);
		hornL.setRotationPoint(-11F, 9F, -6F);
		hornL.setTextureSize(128, 64);
		hornL.mirror = true;
		setRotation(hornL, 0.0174533F, 0F, 0.2617994F);
		
		hornR = new ModelRenderer(this, 0, 0);
		hornR.addBox(-4F, 0F, 0F, 4, 2, 2);
		hornR.setRotationPoint(11F, 9F, -6F);
		hornR.setTextureSize(128, 64);
		hornR.mirror = true;
		setRotation(hornR, 0F, 0F, -0.2617994F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		hornL.render(f5);
		hornR.render(f5);
	}
}
