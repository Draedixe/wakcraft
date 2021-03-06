// Date: 05/04/2014 21:22:43
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package heero.mc.mod.wakcraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelGobbly extends ModelBase {
	// fields
	ModelRenderer legFL;
	ModelRenderer legBL;
	ModelRenderer legFR;
	ModelRenderer legBR;
	ModelRenderer body;
	ModelRenderer HornL;
	ModelRenderer HornR;

	public ModelGobbly() {
		textureWidth = 128;
		textureHeight = 64;

		legFL = new ModelRenderer(this, 0, 4);
		legFL.addBox(0F, 0F, 0F, 3, 5, 3);
		legFL.setRotationPoint(1F, 19F, -6F);
		legFL.setTextureSize(128, 64);
		legFL.mirror = true;
		setRotation(legFL, 0F, 0F, 0F);
		
		legBL = new ModelRenderer(this, 0, 4);
		legBL.addBox(0F, 0F, 0F, 3, 5, 3);
		legBL.setRotationPoint(1F, 19F, -1F);
		legBL.setTextureSize(128, 64);
		legBL.mirror = true;
		setRotation(legBL, 0F, 0F, 0F);
		
		legFR = new ModelRenderer(this, 0, 4);
		legFR.addBox(0F, 0F, 0F, 3, 5, 3);
		legFR.setRotationPoint(-4F, 19F, -6F);
		legFR.setTextureSize(128, 64);
		legFR.mirror = true;
		setRotation(legFR, 0F, 0F, 0F);
		
		legBR = new ModelRenderer(this, 0, 4);
		legBR.addBox(0F, 0F, 0F, 3, 5, 3);
		legBR.setRotationPoint(-4F, 19F, -1F);
		legBR.setTextureSize(128, 64);
		legBR.mirror = true;
		setRotation(legBR, 0F, 0F, 0F);
		
		body = new ModelRenderer(this, 0, 0);
		body.addBox(0F, 0F, 0F, 10, 10, 12);
		body.setRotationPoint(-5F, 11F, -8F);
		body.setTextureSize(128, 64);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		
		HornL = new ModelRenderer(this, 0, 0);
		HornL.addBox(-4F, 0F, 0F, 4, 2, 2);
		HornL.setRotationPoint(7F, 12F, -6F);
		HornL.setTextureSize(128, 64);
		HornL.mirror = true;
		setRotation(HornL, 0F, 0F, -0.2617994F);
		
		HornR = new ModelRenderer(this, 0, 0);
		HornR.addBox(0F, 0F, 0F, 4, 2, 2);
		HornR.setRotationPoint(-3F, 13F, -4F);
		HornR.setTextureSize(128, 64);
		HornR.mirror = true;
		setRotation(HornR, 0F, 3.141593F, -0.2617994F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		legFL.render(f5);
		legBL.render(f5);
		legFR.render(f5);
		legBR.render(f5);
		body.render(f5);
		HornL.render(f5);
		HornR.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		this.legFR.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.legBR.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		this.legFL.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		this.legBL.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
	}

}
